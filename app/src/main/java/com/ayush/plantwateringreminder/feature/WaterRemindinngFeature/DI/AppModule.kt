package com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.DI

import android.app.Application
import androidx.room.Room
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Data.DataSource.PlantDatabase
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Data.Repository.PlantWateringRepositoryImpl
import com.ayush.plantwateringreminder.feature.WaterRemindinngFeature.Domain.Repository.PlantWateringRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePlantDataBase(app:Application):PlantDatabase{
        return Room.databaseBuilder(app,
            PlantDatabase::class.java,
            PlantDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun providePlantRepository(db:PlantDatabase): PlantWateringRepository {
        return PlantWateringRepositoryImpl(db.plantDao)
    }
}