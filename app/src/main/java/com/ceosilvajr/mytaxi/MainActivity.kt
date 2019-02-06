package com.ceosilvajr.mytaxi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ceosilvajr.mytaxi.data.entities.Vehicle
import com.ceosilvajr.mytaxi.features.VehiclePresenter
import com.ceosilvajr.mytaxi.features.VehicleViewModel
import com.ceosilvajr.mytaxi.features.list.ListFragment
import com.ceosilvajr.mytaxi.features.listeners.FetchVehicleView
import com.ceosilvajr.mytaxi.features.map.VehicleMapFragmentBundle
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ceosilvajr@gmail.com
 */
class MainActivity : AppCompatActivity(), ListFragment.Lister, FetchVehicleView {

    private val viewModel: VehicleViewModel by viewModel()
    private val presenter: VehiclePresenter by inject()
    private val compositeDisposable: CompositeDisposable by inject()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initNavController()
        compositeDisposable.add(presenter.fetchVehicles(this))
    }

    private fun initNavController() {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        navController = host.navController
        bottom_nav_view.setupWithNavController(navController)
    }

    override fun onRequestFetchVehicle() {
        compositeDisposable.add(presenter.fetchVehicles(this))
    }

    override fun onVehicleItemClicked(vehicle: Vehicle, vehicles: ArrayList<Vehicle>) {
        navController.navigate(R.id.mapFragment, VehicleMapFragmentBundle.create(vehicle, vehicles))
    }

    override fun onVehiclesAvailable(vehicles: ArrayList<Vehicle>) {
        viewModel.updateVehicles(vehicles)
    }

    override fun alertMessage(message: String) {
        alert(message) {
            okButton { it.dismiss() }
        }.show()
    }

    override fun onSupportNavigateUp() = findNavController(R.id.mapFragment).navigateUp()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
