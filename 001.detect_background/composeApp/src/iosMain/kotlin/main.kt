import androidx.compose.ui.window.ComposeUIViewController
import cc.bcc.cmpexamples.example001.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
