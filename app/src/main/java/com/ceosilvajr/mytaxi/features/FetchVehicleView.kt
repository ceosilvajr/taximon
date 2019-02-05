package com.ceosilvajr.mytaxi.features

import com.ceosilvajr.mytaxi.data.entities.Vehicle

/**
 * @author ceosilvajr@gmail.com
 */
interface FetchVehicleView : BaseView {
    fun onVehiclesAvailable(vehicles: ArrayList<Vehicle>)
}