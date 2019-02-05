package com.ceosilvajr.mytaxi.network.reponses

import com.google.gson.annotations.SerializedName

/**
 * @author ceosilvajr@gmail.com
 */
class FetchVehicleResponse {

    @SerializedName("poiList")
    val results: ArrayList<Vehicle> = arrayListOf()

    inner class Vehicle {
        @SerializedName("id")
        val id: String = ""
        @SerializedName("coordinate")
        val coordinate: Coordinate = Coordinate()
        @SerializedName("fleetType")
        val fleetType: String = ""
        @SerializedName("heading")
        val heading: Double = 0.0

    }

    inner class Coordinate {
        @SerializedName("latitude")
        val latitude: Double = 0.0
        @SerializedName("longitude")
        val longitude: Double = 0.0
    }
}