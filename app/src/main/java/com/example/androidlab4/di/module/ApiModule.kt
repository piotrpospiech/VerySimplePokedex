package com.example.androidlab4.di.module

import com.example.androidlab4.model.ApiServiceInterface
import com.example.androidlab4.utils.Constants.Companion.BASE_URL
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object ApiModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): ApiServiceInterface {
        return retrofit.create(ApiServiceInterface::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}