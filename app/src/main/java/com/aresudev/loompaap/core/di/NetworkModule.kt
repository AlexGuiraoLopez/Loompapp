package com.aresudev.loompaap.core.di

import com.aresudev.loompaap.BuildConfig
import com.aresudev.loompaap.commons.network.LoompaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //Alcance de la instancia.
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder().addInterceptor(logging)
            .connectTimeout(5, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideProductApiClient(retrofit: Retrofit): LoompaApi {
        return retrofit.create(LoompaApi::class.java)
    }
}