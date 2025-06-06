package cc.bcc.cmpexamples.example004

import androidx.compose.runtime.Composable
import coil3.PlatformContext

/**
 * Interface providing Google opening functionality as an example of Context usage.
 * For actual URL opening, use LocalUriHandler.current instead
 */
interface GoogleOpenerType1 {
    fun openGoogle()
}

@Composable
expect fun rememberGoogleOpenerType1(): GoogleOpenerType1

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class GoogleOpenerType2(
    context: PlatformContext,
) {
    fun openGoogle()
}
