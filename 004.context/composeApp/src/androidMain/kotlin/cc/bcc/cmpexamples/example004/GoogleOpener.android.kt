package cc.bcc.cmpexamples.example004

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import coil3.PlatformContext

@Composable
actual fun rememberGoogleOpenerType1(): GoogleOpenerType1 {
    val context = LocalContext.current

    val opener =
        remember(context) {
            object : GoogleOpenerType1 {
                override fun openGoogle() {
                    val intent =
                        android.content.Intent(android.content.Intent.ACTION_VIEW).apply {
                            data = "https://www.google.com".toUri()
                        }
                    context.startActivity(intent)
                }
            }
        }

    return opener
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class GoogleOpenerType2 actual constructor(
    private val context: PlatformContext,
) {
    actual fun openGoogle() {
        val intent =
            android.content.Intent(android.content.Intent.ACTION_VIEW).apply {
                data = "https://www.google.com".toUri()
            }
        context.startActivity(intent)
    }
}
