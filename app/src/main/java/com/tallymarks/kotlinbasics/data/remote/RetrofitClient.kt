package com.tallymarks.kotlinbasics.data.remote

import com.tallymarks.kotlinbasics.App
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        var retrofit: Retrofit? = null
        fun getClient(): Retrofit? {
            if (retrofit == null) {
            }
            retrofit = Retrofit.Builder()
                .baseUrl(App.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
    }
//    companion object {
//        var retrofit: Retrofit? = null
//        fun getClient(): Retrofit? {
//            if (retrofit == null) {
//                val interceptor = HttpLoggingInterceptor()
//                interceptor.level = HttpLoggingInterceptor.Level.BODY
//                val client = OkHttpClient.Builder().apply {
//                    readTimeout(300, TimeUnit.SECONDS)
//                    writeTimeout(300, TimeUnit.SECONDS)
//                    connectTimeout(300, TimeUnit.SECONDS)
//                    addInterceptor(interceptor)
//                    addInterceptor { chain ->
//                        var request = chain.request()
//                        request = request.newBuilder()
//                            .build()
//                        val response = chain.proceed(request)
//                        response
//                    }
//                }
//                retrofit = Retrofit.Builder()
//                    .baseUrl(App.BASE_URL)
//                    .client(client.build())
//
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//
//            }
//
//            return retrofit
//        }
//    }
}
