package com.ceosilvajr.mytaxi.extensions

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.ceosilvajr.mytaxi.R
import com.ceosilvajr.mytaxi.data.entities.Vehicle
import com.ceosilvajr.mytaxi.data.enums.FleetType
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * @author ceosilvajr@gmail.com
 */
fun GoogleMap.addVehicle(context: Context, vehicle: Vehicle) {
    when (vehicle.fleetType) {
        FleetType.POOLING.value -> {
            this.addMarker(MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromBitmap(AppCompatResources.getDrawable(context, R.drawable.ic_svg_car_pooling)!!.toBitmap()))
                    .position(LatLng(vehicle.latitude, vehicle.longitude))
                    .title(vehicle.id.toString()))
        }
        else -> {
            this.addMarker(MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromBitmap(AppCompatResources.getDrawable(context, R.drawable.ic_svg_car_taxi)!!.toBitmap()))
                    .position(LatLng(vehicle.latitude, vehicle.longitude))
                    .title(vehicle.id.toString()))
        }
    }
}

fun GoogleMap.zoomVehicle(vehicle: Vehicle, zoomLevel: Float) {
    val latLng = LatLng(vehicle.latitude, vehicle.longitude)
    this.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
}