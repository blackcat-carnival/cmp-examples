package cc.bcc.cmpexamples.example001.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDidBecomeActiveNotification
import platform.UIKit.UIApplicationDidEnterBackgroundNotification
import platform.UIKit.UIApplicationState

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
class AppLifecycleTrackerImpl : AppLifecycleTracker {
    private val _isInForeground =
        MutableStateFlow(
            UIApplication.sharedApplication.applicationState ==
                UIApplicationState.UIApplicationStateActive,
        )
    override val isInForeground: StateFlow<Boolean> = _isInForeground.asStateFlow()

    private var didBecomeActiveObserver: Any? = null
    private var didEnterBackgroundObserver: Any? = null

    override fun start() {
        val notificationCenter = NSNotificationCenter.defaultCenter

        didBecomeActiveObserver =
            notificationCenter.addObserverForName(
                UIApplicationDidBecomeActiveNotification,
                null,
                NSOperationQueue.mainQueue,
            ) { _ ->
                _isInForeground.value = true
            }

        didEnterBackgroundObserver =
            notificationCenter.addObserverForName(
                UIApplicationDidEnterBackgroundNotification,
                null,
                NSOperationQueue.mainQueue,
            ) { _ ->
                _isInForeground.value = false
            }
    }

    override fun stop() {
        val notificationCenter = NSNotificationCenter.defaultCenter

        didBecomeActiveObserver?.let {
            notificationCenter.removeObserver(it)
            didBecomeActiveObserver = null
        }

        didEnterBackgroundObserver?.let {
            notificationCenter.removeObserver(it)
            didEnterBackgroundObserver = null
        }
    }
}

@Composable
actual fun buildAppLifecycleTracker(): AppLifecycleTracker = remember { AppLifecycleTrackerImpl() }
