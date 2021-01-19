package com.app.petproject.dagger

import android.content.Context
import com.app.petproject.model.RestApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideNewsApi(client: OkHttpClient): RestApi {
        return Retrofit.Builder()
            //TODO change domain
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(RestApi::class.java)
    }



    @Singleton
    @Provides
    fun provideOkHttpClient(context: Context): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}
