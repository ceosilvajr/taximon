package com.ceosilvajr.mytaxi.network

import com.ceosilvajr.mytaxi.network.reponses.FetchVehicleResponse
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author ceosilvajr@gmail.com
 */
interface AppApi {

    @GET("/")
    fun fetchVehicles(@QueryMap queries: HashMap<String, Any>): Flowable<Response<FetchVehicleResponse>>

}