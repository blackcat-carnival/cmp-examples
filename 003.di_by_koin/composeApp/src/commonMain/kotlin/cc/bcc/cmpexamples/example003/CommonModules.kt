package cc.bcc.cmpexamples.example003

import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModules: KoinApplication.() -> Module = {
    module {
        single<CommonExampleClass> {
            CommonExampleClassImpl()
        }
    }
}
