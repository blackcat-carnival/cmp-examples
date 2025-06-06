package cc.bcc.cmpexamples.example004

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import coil3.PlatformContext
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

@Composable
actual fun rememberGoogleOpenerType1(): GoogleOpenerType1 =
    remember {
        object : GoogleOpenerType1 {
            override fun openGoogle() {
                val url = NSURL(string = "https://www.google.com")
                UIApplication.sharedApplication.openURL(url, emptyMap<Any?, Any>()) {}
            }
        }
    }

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class GoogleOpenerType2 actual constructor(
    context: PlatformContext,
) {
    actual fun openGoogle() {
        val url = NSURL(string = "https://www.google.com")
        UIApplication.sharedApplication.openURL(url, emptyMap<Any?, Any>()) {}
    }
}
