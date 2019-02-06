package com.ceosilvajr.mytaxi.features.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ceosilvajr.mytaxi.R
import com.ceosilvajr.mytaxi.data.entities.Vehicle
import com.ceosilvajr.mytaxi.data.enums.FleetType
import com.ceosilvajr.mytaxi.features.listeners.OnVehicleItemClickedListener
import kotlinx.android.synthetic.main.item_vehicle.view.*

/**
 * @author ceosilvajr@gmail.com
 */
class VehicleListAdapter(private val context: Context, private val items: ArrayList<Vehicle>) : RecyclerView.Adapter<VehicleListAdapter.AdapterViewHolder>() {

    private lateinit var listener: OnVehicleItemClickedListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_vehicle, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    fun setListener(listener: OnVehicleItemClickedListener) {
        this.listener = listener
    }

    fun updateData(newItems: ArrayList<Vehicle>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val id = view.tv_vehicle_id!!
        private val fleetType = view.tv_vehicle_fleet_type!!
        private val heading = view.tv_vehicle_heading!!
        private val location = view.tv_vehicle_location!!
        private val avatar = view.iv_fleet_type!!
        private val container = view.item_vehicle_container!!

        fun bindView(vehicle: Vehicle) {
            id.text = vehicle.id.toString()
            fleetType.text = vehicle.fleetType?.toUpperCase()
            heading.text = vehicle.heading.toString()
            location.text = "(${vehicle.latitude} , ${vehicle.longitude})"
            when (vehicle.fleetType) {
                FleetType.POOLING.value -> {
                    fleetType.setBackgroundResource(R.drawable.bg_rounded_accent)
                    avatar.setImageResource(R.drawable.ic_svg_car_pooling)
                }
                else -> {
                    fleetType.setBackgroundResource(R.drawable.bg_rounded_gray)
                    avatar.setImageResource(R.drawable.ic_svg_car_taxi)
                }
            }
            container.setOnClickListener {
                if (::listener.isInitialized) {
                    listener.onVehicleItemClicked(vehicle, items)
                }
            }
        }
    }
}