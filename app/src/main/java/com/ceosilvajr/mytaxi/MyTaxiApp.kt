package com.ceosilvajr.mytaxi

import android.app.Application
import com.ceosilvajr.mytaxi.koin.MainAppModule
import com.ceosilvajr.mytaxi.koin.NetworkAppModule
import org.koin.android.ext.android.startKoin

/**
 * @author ceosilvajr@gmail.com
 */
class MyTaxiApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(NetworkAppModule.init(), MainAppModule.init()))
    }
}