package com.stephen.mokitotutorial

import android.app.Application
import com.stephen.mokitotutorial.di.diModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Written by StephenLeeDev on 2022/01/17.
 */

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(diModule)
        }
    }
}