import androidx.compose.ui.window.ComposeUIViewController
import cc.bcc.cmpexamples.example006.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
