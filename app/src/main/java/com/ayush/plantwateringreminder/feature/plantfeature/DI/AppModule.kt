package com.ayush.plantwateringreminder.feature.plantfeature.DI

import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.PlantApi
import com.ayush.plantwateringreminder.feature.plantfeature.Data.Remote.Repository.PlantRepositoryImpl
import com.ayush.plantwateringreminder.feature.plantfeature.Domain.Repository.PlantRepository
import com.ayush.plantwateringreminder.feature.plantfeature.util.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Provides
    @Singleton
     fun provideRetrofit() :Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
     fun providePlantApi(retrofit: Retrofit): PlantApi {
        return retrofit.create(PlantApi::class.java)
    }

    @Provides
    @Singleton
    fun   providePlantRepo(api: PlantApi): PlantRepository {
       return PlantRepositoryImpl(api)
    }


}