package com.example.localprofiles.data

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.material.imageview.ShapeableImageView
import java.io.Serializable

@Entity(tableName = "profile_item")
data class ProfileItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: Bitmap? = null,
    val name: String? = null,
    val surname: String,
    val email: String,
    val dateOfBirth: String,
    val numberPhone: String,
    val description: String,
    val password: String? = null
): Serializable