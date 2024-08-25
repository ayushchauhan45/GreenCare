package com.ayush.plantwateringreminder.feature.PlantDatabase.DI

import android.app.Application
import androidx.room.Room
import com.ayush.plantwateringreminder.feature.PlantDatabase.Data.DataSource.PlantDatabase
import com.ayush.plantwateringreminder.feature.PlantDatabase.Data.Repository.PlantWateringRepositoryImpl
import com.ayush.plantwateringreminder.feature.PlantDatabase.Domain.Repository.PlantWateringRepository
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