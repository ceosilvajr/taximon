package com.ceosilvajr.mytaxi.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ceosilvajr.mytaxi.data.entities.Vehicle

/**
 * @author ceosilvajr@gmail.com
 */
class VehicleViewModel : ViewModel() {

    private val vehiclesNotifier = MutableLiveData<ArrayList<Vehicle>>()

    fun updateVehicles(vehicles: ArrayList<Vehicle>) {
        if (vehicles.isNotEmpty()) {
            vehiclesNotifier.value = vehicles
        }
    }

    fun getVehicle() = vehiclesNotifier
}