@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package cc.bcc.cmpexamples.example006

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual typealias PlatformBitmap = android.graphics.Bitmap

actual fun ImageBitmap.toPlatformBitmap(): PlatformBitmap = this.asAndroidBitmap()

actual fun PlatformBitmap.toImageBitmap(): ImageBitmap = this.asImageBitmap()
