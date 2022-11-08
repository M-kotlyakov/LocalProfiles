package com.example.localprofiles.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "profile_item")
data class ProfileItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val dateOfBirth: String,
    val numberPhone: String,
    val description: String,
    val password: String
)