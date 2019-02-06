package com.ceosilvajr.mytaxi.features.listeners

import com.ceosilvajr.mytaxi.data.entities.Vehicle

/**
 * @author ceosilvajr@gmail.com
 */
interface OnVehicleItemClickedListener {
    fun onVehicleItemClicked(vehicle: Vehicle, vehicles: ArrayList<Vehicle>)
}