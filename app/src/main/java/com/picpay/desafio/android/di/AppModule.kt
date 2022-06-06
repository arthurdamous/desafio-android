package com.picpay.desafio.android.di

import android.app.Application
import androidx.room.Room
import com.picpay.desafio.android.core.util.Constants.BASE_URL
import com.picpay.desafio.android.core.util.Constants.DATABASE_NAME
import com.picpay.desafio.android.data.local.PicPayDatabase
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.use_case.GetUsers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePicPayDatabase(app: Application): PicPayDatabase {
        return Room.databaseBuilder(
            app,
            PicPayDatabase::class.java,
            DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun providesPicPayApi(
        okHttpClient: OkHttpClient
    ): PicPayService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PicPayService::class.java)
    }

    @Provides
    @Singleton
    fun providesUserRepository(
        picPayService: PicPayService,
        db: PicPayDatabase
    ): UserRepository {
        return UserRepositoryImpl(picPayService, db.userDao)
    }

    @Provides
    @Singleton
    fun providesGetUsers(
        repository: UserRepository
    ): GetUsers {
        return GetUsers(repository)
    }
}