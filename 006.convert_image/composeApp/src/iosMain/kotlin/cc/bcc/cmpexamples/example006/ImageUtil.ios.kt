@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package cc.bcc.cmpexamples.example006

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.ColorAlphaType
import org.jetbrains.skia.ColorType
import org.jetbrains.skia.Data
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image
import org.jetbrains.skia.ImageInfo
import org.jetbrains.skia.makeFromEncoded
import platform.CoreGraphics.CGBitmapContextCreate
import platform.CoreGraphics.CGColorRenderingIntent
import platform.CoreGraphics.CGColorSpaceCreateWithName
import platform.CoreGraphics.CGContextDrawImage
import platform.CoreGraphics.CGDataProviderCreateWithData
import platform.CoreGraphics.CGImageAlphaInfo
import platform.CoreGraphics.CGImageCreate
import platform.CoreGraphics.CGImageGetHeight
import platform.CoreGraphics.CGImageGetWidth
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.kCGBitmapByteOrder32Little
import platform.CoreGraphics.kCGColorSpaceSRGB
import platform.Foundation.NSData
import platform.Foundation.dataWithBytes
import platform.UIKit.UIImage
import platform.UIKit.UIImagePNGRepresentation

actual typealias PlatformBitmap = UIImage

actual fun ImageBitmap.toPlatformBitmap(): PlatformBitmap = this.toPlatformBitmapType2()

actual fun PlatformBitmap.toImageBitmap(): ImageBitmap = this.toImageBitmapType2()

/**
 * Convert ImageBitmap to PlatformBitmap via PNG encoding
 * This method is stable and requires less code, but has performance overhead due to PNG encoding/decoding
 */
@OptIn(ExperimentalForeignApi::class)
fun ImageBitmap.toPlatformBitmapType1(): PlatformBitmap {
    val skiaImage = Image.makeFromBitmap(this.asSkiaBitmap())
    val pngData: Data? = skiaImage.encodeToData(EncodedImageFormat.PNG)
    val pngBytes = pngData?.bytes ?: throw Exception("Failed to encode ImageBitmap to PNG")

    val nsData =
        pngBytes.usePinned { pinned ->
            NSData.dataWithBytes(pinned.addressOf(0), pngBytes.size.toULong())
        }
    return UIImage(data = nsData)
}

/**
 * Convert ImageBitmap to PlatformBitmap by directly creating CGImage
 * This method has better performance than PNG approach but may not work in all environments
 */
@OptIn(ExperimentalForeignApi::class)
fun ImageBitmap.toPlatformBitmapType2(): PlatformBitmap {
    val skiaBitmap = this.asSkiaBitmap()
    val width = skiaBitmap.width
    val height = skiaBitmap.height

    // Extract bitmap pixel data as 32bit BGRA_8888 with premultiplied alpha
    val imageInfo = ImageInfo.makeN32Premul(width, height)
    val finalPixelData =
        skiaBitmap.readPixels(imageInfo) ?: throw Exception("Failed to read pixels")

    // Pin ByteArray to prevent GC interference and create NSData with memory copy
    val nsData =
        finalPixelData.usePinned { pinned ->
            NSData.dataWithBytes(
                bytes = pinned.addressOf(0),
                length = (width * height * 4).toULong(),
            )
        }

    val provider =
        CGDataProviderCreateWithData(
            info = null,
            data = nsData.bytes,
            size = nsData.length,
            releaseData = null,
        )

    val cgImage =
        CGImageCreate(
            width = width.toULong(),
            height = height.toULong(),
            bitsPerComponent = 8u, // 8 bits per R,G,B,A component
            bitsPerPixel = 32u, // Total 32 bits
            bytesPerRow = (width * 4).toULong(),
            space = CGColorSpaceCreateWithName(kCGColorSpaceSRGB), // Use sRGB color space
            bitmapInfo =
                CGImageAlphaInfo.kCGImageAlphaPremultipliedFirst.value // Alpha first, premultiplied
                    or kCGBitmapByteOrder32Little, // Little endian for both ARM and x86/x64
            provider = provider,
            decode = null, // No decoding
            shouldInterpolate = false, // No pixel interpolation
            intent = CGColorRenderingIntent.kCGRenderingIntentDefault, // Default color space conversion
        )

    return UIImage(cGImage = cgImage)
}

/**
 * Convert PlatformBitmap to ImageBitmap via PNG encoding
 * This method is stable and simple but has performance overhead due to PNG encoding/decoding
 */
fun PlatformBitmap.toImageBitmapType1(): ImageBitmap {
    val pngData = UIImagePNGRepresentation(this) ?: throw Exception("Failed to convert image")
    return Image.makeFromEncoded(pngData).toComposeImageBitmap()
}

/**
 * Convert PlatformBitmap to ImageBitmap by directly reading CGImage pixel data
 * This method has better performance than PNG approach but requires more complex pixel data handling
 */
@OptIn(ExperimentalForeignApi::class)
fun PlatformBitmap.toImageBitmapType2(): ImageBitmap {
    val cgImage = this.CGImage ?: throw Exception("Failed to get CGImage from UIImage")
    val width = CGImageGetWidth(cgImage).toInt()
    val height = CGImageGetHeight(cgImage).toInt()
    val bytesPerRow = width * 4
    val dataSize = height * bytesPerRow

    // Create bitmap context to extract pixel data
    val colorSpace = CGColorSpaceCreateWithName(kCGColorSpaceSRGB)
    val context =
        CGBitmapContextCreate(
            data = null,
            width = width.toULong(),
            height = height.toULong(),
            bitsPerComponent = 8u,
            bytesPerRow = bytesPerRow.toULong(),
            space = colorSpace,
            bitmapInfo =
                CGImageAlphaInfo.kCGImageAlphaPremultipliedFirst.value or kCGBitmapByteOrder32Little,
        ) ?: throw Exception("Failed to create bitmap context")

    // Draw CGImage to context
    CGContextDrawImage(context, CGRectMake(0.0, 0.0, width.toDouble(), height.toDouble()), cgImage)

    // Get pixel data from context
    val pixelData = ByteArray(dataSize)
    pixelData.usePinned { pinned ->
        val contextData = platform.CoreGraphics.CGBitmapContextGetData(context)
        platform.posix.memcpy(pinned.addressOf(0), contextData, dataSize.toULong())
    }

    // Create Skia bitmap from pixel data
    val imageInfo =
        ImageInfo(
            width = width,
            height = height,
            colorType = ColorType.BGRA_8888,
            alphaType = ColorAlphaType.PREMUL,
        )

    val bitmap = Bitmap()
    bitmap.allocPixels(imageInfo)
    bitmap.installPixels(pixelData)

    return bitmap.asComposeImageBitmap()
}
