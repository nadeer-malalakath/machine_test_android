package `in`.nadeerm.app.webandcraft

import `in`.nadeerm.app.webandcraft.service.di.appModules
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WebAndCraftApp : Application() {
    companion object {
        lateinit var instance: WebAndCraftApp
        fun get(): WebAndCraftApp = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@WebAndCraftApp)
            modules(appModules)
        }

    }
}