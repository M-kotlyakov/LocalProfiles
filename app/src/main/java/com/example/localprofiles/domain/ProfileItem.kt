package com.example.localprofiles.domain

import android.graphics.Bitmap
import com.google.android.material.imageview.ShapeableImageView
import java.io.Serializable

data class ProfileItem(
    val id: Int = UNDEFINED_ID,
    val image: Bitmap? = null,
    val name: String? = null,
    val surname: String,
    val email: String,
    val dateOfBirth: String,
    val numberPhone: String,
    val description: String,
    val password: String? = null
): Serializable {

    companion object {

        const val UNDEFINED_ID = 0
    }
}
