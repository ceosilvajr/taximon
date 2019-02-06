package com.ceosilvajr.mytaxi.features.map

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ceosilvajr.mytaxi.R
import com.ceosilvajr.mytaxi.data.entities.Vehicle
import com.ceosilvajr.mytaxi.data.enums.MapZoomType
import com.ceosilvajr.mytaxi.extensions.addVehicle
import com.ceosilvajr.mytaxi.extensions.zoomVehicle
import com.ceosilvajr.mytaxi.features.listeners.OnRequestFetchVehicleListener
import com.ceosilvajr.mytaxi.features.VehicleViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * @author ceosilvajr@gmail.com
 */
class VehicleMapFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: VehicleViewModel by sharedViewModel()

    private lateinit var map: GoogleMap
    private lateinit var mainVehicle: Vehicle
    private lateinit var listener: OnRequestFetchVehicleListener

    private var vehicles: ArrayList<Vehicle> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mainVehicle = VehicleMapFragmentBundle.getVehicle(it)
            vehicles = VehicleMapFragmentBundle.getVehicles(it)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnRequestFetchVehicleListener) {
            listener = context
        } else {
            throw RuntimeException("OnRequestFetchVehicleListener should be implemented.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMapFragment()
    }

    override fun onMapReady(newMap: GoogleMap) {
        map = newMap
        if (isOpenFromListFragment()) {
            map.zoomVehicle(mainVehicle, MapZoomType.MAIN_VEHICLE.value)
            plotVehiclesToMap(vehicles)
        }
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getVehicle().observe(this, Observer {
            onVehiclesAvailable(it)
        })
    }

    private fun isOpenFromListFragment() = ::mainVehicle.isInitialized && vehicles.isNotEmpty()

    private fun initMapFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun onVehiclesAvailable(newVehicles: ArrayList<Vehicle>) {
        vehicles.clear()
        vehicles.addAll(newVehicles)
        plotVehiclesToMap(vehicles)
        if (!::mainVehicle.isInitialized && vehicles.isNotEmpty()) {
            map.zoomVehicle(vehicles.random(), MapZoomType.ALL_VEHICLE.value)
        }
    }

    private fun plotVehiclesToMap(vehicles: ArrayList<Vehicle>) {
        map.clear()
        vehicles.forEach {
            map.addVehicle(context!!, it)
        }
    }
}
