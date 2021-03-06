package com.ceosilvajr.mytaxi.koin

import com.ceosilvajr.mytaxi.BuildConfig
import com.ceosilvajr.mytaxi.network.AppApi
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author ceosilvajr@gmail.com
 */
object NetworkAppModule {

    fun init(): Module {
        return module {
            single { httpClient() }
            single { createRetrofit(get()) }
            factory { createAuthApiService(get()) }
        }
    }

    private fun createAuthApiService(retrofit: Retrofit): AppApi = retrofit.create(AppApi::class.java)

    private fun httpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder
                .connectTimeout(Properties.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Properties.READ_TIMEOUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return clientBuilder.build()
    }

    private fun createRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
            .baseUrl(Properties.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    private object Properties {
        const val BASE_URL = "https://fake-poi-api.mytaxi.com/"
        const val READ_TIMEOUT = 60L
        const val CONNECTION_TIMEOUT = 60L
    }
}
