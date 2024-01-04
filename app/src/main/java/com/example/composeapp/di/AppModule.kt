package com.example.composeapp.di

import com.example.composeapp.Utils
import com.example.composeapp.data.repo.remote.RemoteRepository
import com.example.composeapp.data.repo.remote.RemoteRepositoryImpl
import com.example.composeapp.data.source.remote.ApiService
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

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideBaseUrl() = Utils.Constant.BASE_URL

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient:OkHttpClient,baseUrl:String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor?): OkHttpClient {
        val builder = OkHttpClient.Builder()
        httpLoggingInterceptor?.let { interceptor ->
            builder.addNetworkInterceptor(interceptor)
        }
        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.writeTimeout(15, TimeUnit.SECONDS)
        builder.readTimeout(15, TimeUnit.SECONDS)
        builder.pingInterval(15, TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(false)
        builder.followRedirects(false)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    @Singleton
    @Provides
    fun provideRemoteRepository(apiService: ApiService):RemoteRepository = RemoteRepositoryImpl(apiService)
}