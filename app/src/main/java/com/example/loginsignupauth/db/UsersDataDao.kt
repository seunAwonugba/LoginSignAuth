package com.example.loginsignupauth.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.loginsignupauth.model.local.UserResponse

@Dao
interface UsersDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUsers(userResponse: UserResponse)

    @Query("SELECT * FROM saved_user_data LIMIT 1")
    fun getUser(): UserResponse?

    @Query("DELETE FROM saved_user_data")
    suspend fun deleteUser()
}