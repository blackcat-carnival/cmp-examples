package cc.bcc.cmpexamples.example006

import androidx.compose.ui.graphics.ImageBitmap

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class PlatformBitmap

expect fun ImageBitmap.toPlatformBitmap(): PlatformBitmap

expect fun PlatformBitmap.toImageBitmap(): ImageBitmap
