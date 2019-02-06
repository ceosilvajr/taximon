package com.ceosilvajr.mytaxi.koin

import com.ceosilvajr.mytaxi.features.VehiclePresenter
import com.ceosilvajr.mytaxi.network.repository.AppRepository
import com.ceosilvajr.mytaxi.features.VehicleViewModel
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

/**
 * @author ceosilvajr@gmail.com
 */
object MainAppModule {

    fun init(): Module {
        return module {
            single { Gson() }
            viewModel { VehicleViewModel() }
            factory { CompositeDisposable() }
            factory { AppRepository(get()) }
            factory { VehiclePresenter(get()) }
        }
    }
}
