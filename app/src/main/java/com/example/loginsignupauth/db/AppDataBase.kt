package com.example.loginsignupauth.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.loginsignupauth.model.local.UserResponse

@Database(entities = [UserResponse::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun usersDataDao(): UsersDataDao

}