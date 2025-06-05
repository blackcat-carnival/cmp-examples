package cc.bcc.cmpexamples.example003

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class Application : android.app.Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(
                commonModules(),
                module {
                    single<PlatformExampleClass> { AndroidExampleClassImpl() }
                },
            )
        }
    }
}
