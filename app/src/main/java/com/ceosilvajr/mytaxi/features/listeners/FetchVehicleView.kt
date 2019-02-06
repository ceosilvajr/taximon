package com.ceosilvajr.mytaxi.features.listeners

import com.ceosilvajr.mytaxi.data.entities.Vehicle

/**
 * @author ceosilvajr@gmail.com
 */
interface FetchVehicleView {
    fun alertMessage(message: String)
    fun onVehiclesAvailable(vehicles: ArrayList<Vehicle>)
}