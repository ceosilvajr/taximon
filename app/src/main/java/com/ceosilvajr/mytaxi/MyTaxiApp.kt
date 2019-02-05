package com.ceosilvajr.mytaxi

import android.app.Application
import com.xbvip.android.koin.MainAppModule
import com.xbvip.android.koin.NetworkAppModule
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