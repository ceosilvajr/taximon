package com.ceosilvajr.mytaxi.network.repository

import com.ceosilvajr.mytaxi.data.HamburgLocationBounds
import com.ceosilvajr.mytaxi.network.AppApi
import com.ceosilvajr.mytaxi.network.reponses.FetchVehicleResponse
import io.reactivex.Flowable
import retrofit2.Response

/**
 * @author ceosilvajr@gmail.com
 */
class AppRepository(private val api: AppApi) {

    fun fetchVehicles(): Flowable<Response<FetchVehicleResponse>> {
        val queryMap: HashMap<String, Any> = hashMapOf()
        queryMap["p1Lat"] = HamburgLocationBounds.LAT_1.value
        queryMap["p1Lon"] = HamburgLocationBounds.LON_1.value
        queryMap["p2Lat"] = HamburgLocationBounds.LAT_2.value
        queryMap["p2Lon"] = HamburgLocationBounds.LON_2.value
        return api.fetchVehicles(queryMap)
    }
}