package com.example.weatherapp.di/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:15 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/5/20 12:34 PM
 *
 */



object NetModule {

   /* val networkModule = module {
        //GENERATE CACHE SINGLETON
        single { provideCache(get()) }

        //GENERATE OKHTTP CLIENT SINGLETON
        single { provideOkHttpClient(get()) }

        //GENERATE RETROFIT CLIENT SINGLETON
        single { provideRetrofit(get(), BuildConfig.API_BASE_URL) }

        //GENERATE API SERVICE SINGLETON
        single { provideAppService(get()) }
    }*/


    /*private fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY }

        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor{
                    chain ->
                val newRequest = chain.request().newBuilder()
                    .header("X-API-Key", BuildConfig.NEWS_API_KEY)
                chain.proceed(newRequest.build())
            }
            .addInterceptor(logger)
            .build()
    }*/

  /*  private fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
    }

    private fun provideCache(context: Context): Cache {
        val cacheSize: Long = 10 * 1024 * 1024
        return Cache(context.cacheDir, cacheSize)
    }


    private fun provideAppService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)*/
}