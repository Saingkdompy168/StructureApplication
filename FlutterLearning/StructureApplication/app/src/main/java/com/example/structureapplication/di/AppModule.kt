package com.example.structureapplication.di

import android.app.Application
import android.os.Build
import androidx.room.Room
import com.example.structureapplication.BuildConfig
import com.example.structureapplication.api.ApiService
import com.example.structureapplication.localroom.database.AppDatabase
import com.example.structureapplication.localroom.repository.MovieRepository
import com.example.structureapplication.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }


    @Provides
    fun providesOkhttpInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Request-Type", "Android")
                .addHeader("Content-Type", "application/json")
                .addHeader("connection", "keep-alive")
                .addHeader("x-app-version", BuildConfig.VERSION_NAME)
                .addHeader("x-os-version", "${Build.VERSION.SDK_INT}")
                .addHeader("x-timezone", TimeZone.getDefault().id)
                .addHeader("x-platform", "ANDROID")
                .addHeader("x-udid", "def4831ed735437f")
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }


    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

//    @Provides
//    @Singleton
//    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "movie_db"
        ).fallbackToDestructiveMigration().build()
    }


    @Provides
    @Singleton
    fun provideTodoRepository(db: AppDatabase): MovieRepository {
        return MovieRepository(db.dao)
    }


}