package com.ayush.plantwateringreminder.feature.PlantAlarm.DI

import android.app.Application
import androidx.room.Room
import com.ayush.plantwateringreminder.feature.PlantAlarm.Data.DataSource.ReminderDataBase
import com.ayush.plantwateringreminder.feature.PlantAlarm.Data.Repository.ReminderRepositoryImpl
import com.ayush.plantwateringreminder.feature.PlantAlarm.Domain.Repository.ReminderRepository
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
    fun provideReminderDatabase(app:Application): ReminderDataBase{
        return Room.databaseBuilder(
            app,
            ReminderDataBase::class.java,
            ReminderDataBase.DATABASE_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideReminderRepository(db:ReminderDataBase): ReminderRepository {
        return  ReminderRepositoryImpl(db.reminderDao)
    }

}