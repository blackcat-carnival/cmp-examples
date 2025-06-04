package cc.bcc.cmpexamples.example001.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
class AppLifecycleTrackerImpl(
    private val activity: Activity,
) : AppLifecycleTracker {
    private val _isInForeground = MutableStateFlow(true)

    override val isInForeground: StateFlow<Boolean> = _isInForeground.asStateFlow()

    private val activityLifecycleCallbacks =
        object : Application.ActivityLifecycleCallbacks {
            override fun onActivityStarted(activity: Activity) {
                if (this@AppLifecycleTrackerImpl.activity == activity) {
                    _isInForeground.value = true
                }
            }

            override fun onActivityStopped(activity: Activity) {
                if (this@AppLifecycleTrackerImpl.activity == activity) {
                    _isInForeground.value = false
                }
            }

            override fun onActivityCreated(
                activity: Activity,
                savedInstanceState: Bundle?,
            ) {
            }

            override fun onActivityResumed(activity: Activity) {}

            override fun onActivityPaused(activity: Activity) {}

            override fun onActivitySaveInstanceState(
                activity: Activity,
                outState: Bundle,
            ) {
            }

            override fun onActivityDestroyed(activity: Activity) {}
        }

    override fun start() {
        activity.registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    override fun stop() {
        activity.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }
}

@Composable
actual fun buildAppLifecycleTracker(): AppLifecycleTracker {
    val activity = LocalContext.current as Activity
    return remember(activity) {
        AppLifecycleTrackerImpl(activity)
    }
}
