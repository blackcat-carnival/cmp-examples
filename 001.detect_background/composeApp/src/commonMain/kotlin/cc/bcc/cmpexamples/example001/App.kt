package cc.bcc.cmpexamples.example001

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import cc.bcc.cmpexamples.example001.lifecycle.LocalAppLifecycleTracker
import cc.bcc.cmpexamples.example001.lifecycle.rememberAppLifecycleTracker
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
internal fun App() {
    val tracker = rememberAppLifecycleTracker()
    CompositionLocalProvider(LocalAppLifecycleTracker provides tracker) {
        TrackCheck()
    }
}
