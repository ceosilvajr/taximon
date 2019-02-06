package com.ceosilvajr.mytaxi.features.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ceosilvajr.mytaxi.R
import com.ceosilvajr.mytaxi.features.listeners.OnRequestFetchVehicleListener
import com.ceosilvajr.mytaxi.features.listeners.OnVehicleItemClickedListener
import com.ceosilvajr.mytaxi.features.VehicleViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * @author ceosilvajr@gmail.com
 */
class ListFragment : Fragment() {

    private val viewModel: VehicleViewModel by sharedViewModel()

    private lateinit var adapter: VehicleListAdapter
    private lateinit var listener: Lister

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Lister) {
            listener = context
        } else {
            throw RuntimeException("ListFragment.Listener should be implemented.")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getVehicle().observe(this, Observer {
            swipe_refresh.isRefreshing = false
            adapter.updateData(it)
        })
    }

    private fun initializeView() {
        adapter = VehicleListAdapter(context!!, arrayListOf())
        adapter.setListener(listener)
        rv_vehicles.adapter = adapter
        rv_vehicles.setHasFixedSize(true)
        swipe_refresh.isRefreshing = true
        swipe_refresh.setOnRefreshListener {
            listener.onRequestFetchVehicle()
        }
    }

    interface Lister : OnVehicleItemClickedListener, OnRequestFetchVehicleListener
}
