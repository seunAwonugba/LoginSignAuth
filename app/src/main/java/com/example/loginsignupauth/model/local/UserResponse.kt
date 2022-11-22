package com.example.loginsignupauth.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_user_data")
data class UserResponse(
    val active: Boolean = false,
    val country: String = "",
    val address:String = "",
    val state:String = "",
    val createdAt: String = "",
    val createdBy: String = "",
    val email: String = "",
    val firstname: String = "",
    @PrimaryKey
    val id: String = "",
    val lastname: String = "",
    val primaryPhone: String = "",
    val updatedAt: String = "",
    val imageUrl: String = ""
)