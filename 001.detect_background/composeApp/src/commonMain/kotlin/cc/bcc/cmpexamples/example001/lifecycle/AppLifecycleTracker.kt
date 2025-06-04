package cc.bcc.cmpexamples.example001.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.compositionLocalOf
import kotlinx.coroutines.flow.StateFlow

val LocalAppLifecycleTracker =
    compositionLocalOf<AppLifecycleTracker> {
        throw Exception("LocalAppLifecycleTracker is not initialized")
    }

interface AppLifecycleTracker {
    fun start()

    fun stop()

    val isInForeground: StateFlow<Boolean>
}

@Composable
expect fun buildAppLifecycleTracker(): AppLifecycleTracker

@Composable
fun rememberAppLifecycleTracker(): AppLifecycleTracker {
    val lifecycleTracker = buildAppLifecycleTracker()

    DisposableEffect(lifecycleTracker) {
        lifecycleTracker.start()

        onDispose {
            lifecycleTracker.stop()
        }
    }

    return lifecycleTracker
}
