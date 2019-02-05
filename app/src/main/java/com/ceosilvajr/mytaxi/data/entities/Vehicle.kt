package com.ceosilvajr.mytaxi.data.entities

import com.ceosilvajr.mytaxi.network.reponses.FetchVehicleResponse

/**
 * @author ceosilvajr@gmail.com
 */
class Vehicle(
        val id: String = "",
        val fleetType: String = "",
        val heading: Double = 0.0,
        val latitude: Double = 0.0,
        val longitude: Double = 0.0
) {
    companion object {
        fun apiResultToVehicleEntity(result: FetchVehicleResponse.Vehicle): Vehicle {
            val coordinate = result.coordinate
            return Vehicle(result.id, result.fleetType, result.heading, coordinate.latitude, coordinate.longitude)
        }
    }
}