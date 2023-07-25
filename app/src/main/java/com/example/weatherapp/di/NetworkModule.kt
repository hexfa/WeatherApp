package com.example.weatherapp.di



import android.content.Context
import com.example.weatherapp.network.ApiHelper
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetModule {

    val networkModule = module {
        //GENERATE CACHE SINGLETON
        single { provideCache(get()) }

        //GENERATE OKHTTP CLIENT SINGLETON
        single { provideOkHttpClient(get()) }

        //GENERATE RETROFIT CLIENT SINGLETON
        single { provideRetrofit(get(), "https://api.open-meteo.com/") }

        //GENERATE API SERVICE SINGLETON
        single { provideAppService(get()) }
    }


    private fun provideOkHttpClient(cache: Cache): OkHttpClient {


        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor{
                    chain ->
                val newRequest = chain.request().newBuilder()
                chain.proceed(newRequest.build())
            }
            .build()
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        val moshi=Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
    }

    private fun provideCache(context: Context): Cache {
        val cacheSize: Long = 10 * 1024 * 1024
        return Cache(context.cacheDir, cacheSize)
    }


    private fun provideAppService(retrofit: Retrofit): ApiHelper =
        retrofit.create(ApiHelper::class.java)
}