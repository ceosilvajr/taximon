package com.ceosilvajr.mytaxi.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ceosilvajr.mytaxi.R
import com.ceosilvajr.mytaxi.data.entities.Vehicle
import com.ceosilvajr.mytaxi.features.FetchVehicleView
import com.ceosilvajr.mytaxi.features.VehiclePresenter
import com.ceosilvajr.mytaxi.features.list.VehicleListAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.koin.android.ext.android.inject

/**
 * @author ceosilvajr@gmail.com
 */
class ListFragment : BaseFragment(), FetchVehicleView {

    private val presenter: VehiclePresenter by inject()
    private lateinit var adapter: VehicleListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = VehicleListAdapter(context!!, arrayListOf())
        initializeView()
        compositeDisposable.add(presenter.fetchVehicles(this))
    }

    private fun initializeView() {
        rv_vehicles.adapter = adapter
        rv_vehicles.setHasFixedSize(true)
        swipe_refresh.setOnRefreshListener {
            compositeDisposable.add(presenter.fetchVehicles(this))
        }
    }

    override fun onVehiclesAvailable(vehicles: ArrayList<Vehicle>) {
        adapter.updateData(vehicles)
    }

    override fun showLoading() {
        swipe_refresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipe_refresh.isRefreshing = false
    }

    override fun alertMessage(message: String) {
        activity!!.alert(message) {
            okButton { it.dismiss() }
        }.show()
    }
}
