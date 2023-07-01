package com.example.moviespetapp.di

import android.content.Context
import com.example.moviespetapp.ThisApp
import com.example.moviespetapp.data.network.ApiService
import com.example.moviespetapp.Constants
import com.example.moviespetapp.data.RepositoryImpl
import com.example.moviespetapp.data.network.NetworkConnectivityObserver
import com.example.moviespetapp.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext app: Context): ThisApp {
        return app as ThisApp
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(@ApplicationContext context: Context) =
        NetworkConnectivityObserver(context)

}