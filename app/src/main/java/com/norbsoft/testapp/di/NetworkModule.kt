package com.norbsoft.testapp.di

import com.norbsoft.testapp.BuildConfig
import com.norbsoft.testapp.repository.network.ChannelsNetworkRepository
import com.norbsoft.testapp.repository.network.api.ChannelsAPI
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { createOkHttpWithInterceptor() }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    single<ChannelsAPI> {
        get<Retrofit>().create(ChannelsAPI::class.java)
    }

    single { ChannelsNetworkRepository(get()) }

}


private fun createOkHttpWithInterceptor(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.readTimeout(5, TimeUnit.SECONDS)
    builder.writeTimeout(5, TimeUnit.SECONDS)
    builder.callTimeout(5, TimeUnit.SECONDS)
    if(BuildConfig.DEBUG) {
        builder.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    }

    return builder.build()
}