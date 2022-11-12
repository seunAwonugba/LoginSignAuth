package com.example.loginsignupauth.db

import android.content.Context
import androidx.room.Room
import com.example.loginsignupauth.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        Constants.SAVED_USER_DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideDao(dataBase: AppDataBase) = dataBase.usersDataDao()
}