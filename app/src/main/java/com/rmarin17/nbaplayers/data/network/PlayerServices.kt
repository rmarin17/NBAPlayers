package com.rmarin17.nbaplayers.data.network

import com.rmarin17.nbaplayers.data.network.ApiConstants.SEARCH
import com.rmarin17.nbaplayers.data.network.ApiConstants.SEARCH_PATCH
import com.rmarin17.nbaplayers.data.network.models.PlayerResponseModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Interface to define the services call to retrofit
 */
interface PlayerServices {

    @GET(SEARCH_PATCH)
    fun getPlayersByQuery(@Query(SEARCH) query: String): Observable<List<PlayerResponseModel>>

    @GET(SEARCH_PATCH)
    fun getPlayers(): Observable<PlayerResponseModel>

}
