@file:Suppress("unused")

import cc.bcc.cmpexamples.example003.PlatformExampleClass
import cc.bcc.cmpexamples.example003.commonModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoinType1() {
    startKoin {
        modules(
            commonModules(),
            module {
                single<PlatformExampleClass> { IosKotlinExampleClass() }
            },
        )
    }
}

fun initKoinType2(exampleClassFactory: () -> PlatformExampleClass) {
    startKoin {
        modules(
            commonModules(),
            module {
                single { exampleClassFactory() }
            },
        )
    }
}
