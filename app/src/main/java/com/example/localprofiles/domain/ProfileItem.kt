package com.example.localprofiles.domain

import java.io.Serializable

data class ProfileItem(
    val id: Int = UNDEFINED_ID,
    val name: String? = null,
    val surname: String,
    val email: String,
    val dateOfBirth: String,
    val numberPhone: String,
    val description: String,
    val password: String
): Serializable {

    companion object {

        const val UNDEFINED_ID = 0
    }
}
