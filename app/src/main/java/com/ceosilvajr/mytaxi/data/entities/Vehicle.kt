package com.ceosilvajr.mytaxi.data.entities

import android.os.Parcel
import android.os.Parcelable
import com.ceosilvajr.mytaxi.network.reponses.FetchVehicleResponse

/**
 * @author ceosilvajr@gmail.com
 */
data class Vehicle(
        val id: Int = 0,
        val fleetType: String? = "",
        val heading: Double = 0.0,
        val latitude: Double = 0.0,
        val longitude: Double = 0.0
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(fleetType)
        dest.writeDouble(heading)
        dest.writeDouble(latitude)
        dest.writeDouble(longitude)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Vehicle> {
        override fun createFromParcel(parcel: Parcel): Vehicle {
            return Vehicle(parcel)
        }

        override fun newArray(size: Int): Array<Vehicle?> {
            return arrayOfNulls(size)
        }

        fun apiResultToVehicleEntity(result: FetchVehicleResponse.Vehicle): Vehicle {
            val coordinate = result.coordinate
            return Vehicle(result.id, result.fleetType, result.heading, coordinate.latitude, coordinate.longitude)
        }
    }
}