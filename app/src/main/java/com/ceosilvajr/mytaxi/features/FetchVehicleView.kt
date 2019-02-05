package com.ceosilvajr.mytaxi.features

import com.ceosilvajr.mytaxi.data.entities.Vehicle
import com.ceosilvajr.mytaxi.view.BaseView

/**
 * @author ceosilvajr@gmail.com
 */
interface FetchVehicleView : BaseView {
    fun onVehiclesAvailable(vehicles: ArrayList<Vehicle>)
}