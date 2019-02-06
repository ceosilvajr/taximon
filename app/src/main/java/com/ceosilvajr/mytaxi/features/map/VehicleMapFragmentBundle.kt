package com.ceosilvajr.mytaxi.features.map

import android.os.Bundle
import com.ceosilvajr.mytaxi.data.entities.Vehicle

/**
 * @author ceosilvajr@gmail.com
 */
object VehicleMapFragmentBundle {

    private const val VEHICLE_BUNDLE = "vehicle"
    private const val VEHICLE_LIST_BUNDLE = "vehicle_list"

    fun create(vehicle: Vehicle, vehicles: ArrayList<Vehicle>): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(VEHICLE_BUNDLE, vehicle)
        bundle.putParcelableArrayList(VEHICLE_LIST_BUNDLE, vehicles)
        return bundle
    }

    fun getVehicle(it: Bundle) = it.getParcelable(VEHICLE_BUNDLE) as Vehicle

    fun getVehicles(it: Bundle) = it.getParcelableArrayList<Vehicle>(VEHICLE_LIST_BUNDLE) as ArrayList<Vehicle>
}