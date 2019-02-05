package com.ceosilvajr.mytaxi.features

import com.ceosilvajr.mytaxi.data.entities.Vehicle
import com.ceosilvajr.mytaxi.network.reponses.FetchVehicleResponse
import com.ceosilvajr.mytaxi.network.repository.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

/**
 * @author ceosilvajr@gmail.com
 */
class VehiclePresenter(private val repository: AppRepository) {

    fun fetchVehicles(view: FetchVehicleView): Disposable {
        view.showLoading()
        return repository.fetchVehicles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.hideLoading()
                    if (it.isSuccessful) {
                        val response = it.body() as FetchVehicleResponse
                        val results: ArrayList<Vehicle> = arrayListOf()
                        response.results.forEach {
                            results.add(Vehicle.apiResultToVehicleEntity(it))
                        }
                        view.onVehiclesAvailable(results)
                    } else {
                        view.alertMessage(it.message())
                    }
                }, {
                    view.hideLoading()
                    view.alertMessage(it.message ?: "Unknown Error.")
                })
    }
}