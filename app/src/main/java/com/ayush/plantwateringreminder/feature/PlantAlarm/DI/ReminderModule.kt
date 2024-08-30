package com.ayush.plantwateringreminder.feature.PlantAlarm.DI


import com.ayush.plantwateringreminder.feature.PlantAlarm.Data.Repository.ReminderRepositoryImpl
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Repository.ReminderRepository
import com.ayush.plantwateringreminder.feature.PlantDatabase.Data.DataSource.PlantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReminderModule {


    @Provides
    @Singleton
    fun provideReminderRepository(db:PlantDatabase): ReminderRepository {
        return  ReminderRepositoryImpl(db.reminderDao)
    }


}