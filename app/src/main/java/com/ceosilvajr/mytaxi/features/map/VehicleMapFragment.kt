package com.ceosilvajr.mytaxi.features.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ceosilvajr.mytaxi.R
import com.ceosilvajr.mytaxi.data.entities.Vehicle
import com.ceosilvajr.mytaxi.extensions.addVehicle
import com.ceosilvajr.mytaxi.extensions.zoomVehicle
import com.ceosilvajr.mytaxi.features.FetchVehicleView
import com.ceosilvajr.mytaxi.features.VehiclePresenter
import com.ceosilvajr.mytaxi.view.BaseFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.fragment_map.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.koin.android.ext.android.inject

/**
 * @author ceosilvajr@gmail.com
 */
class VehicleMapFragment : BaseFragment(), OnMapReadyCallback, FetchVehicleView {

    private val presenter: VehiclePresenter by inject()
    private lateinit var map: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = SupportMapFragment.newInstance()
        fragmentManager!!.beginTransaction().add(R.id.map_container, mapFragment).commitAllowingStateLoss()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        this.map = map!!
        compositeDisposable.add(presenter.fetchVehicles(this))
    }

    override fun onVehiclesAvailable(vehicles: ArrayList<Vehicle>) {
        vehicles.forEach {
            map.addVehicle(context!!, it)
        }
        map.zoomVehicle(vehicles.first())
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun alertMessage(message: String) {
        activity!!.alert(message) {
            okButton { it.dismiss() }
        }.show()
    }
}
