package com.norbsoft.testapp.repository.network.api

import com.norbsoft.testapp.repository.network.model.NetworkChannels
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ChannelsAPI {

    @GET("channels.json")
    fun getChannels() : Single<Response<NetworkChannels>>

}