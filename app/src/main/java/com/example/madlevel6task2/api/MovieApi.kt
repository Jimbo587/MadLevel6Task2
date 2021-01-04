package com.example.madlevel6task2.api

import android.content.Context
import com.example.madlevel6task2.R
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {
    companion object {
        /**
         * @return [TriviaApiService] The service class off the retrofit client.
         */

        private val baseUrl = "https://api.themoviedb.org/3/"
        private val apiKey= "761e91d2aab6384b390db32e4129b6c5"

        class MovieInterceptor: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val url = chain.request().url.newBuilder().addQueryParameter("api_key",
                    apiKey).build()
                val newRequest = chain.request().newBuilder().url(url).build()
                return chain.proceed(newRequest)
            }
        }

        fun createApi(): MovieApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(MovieInterceptor())
                .build()

            // Create the Retrofit instance
            val triviaApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Return the Retrofit TriviaApiService
            return triviaApi.create(MovieApiService::class.java)
        }
    }
}