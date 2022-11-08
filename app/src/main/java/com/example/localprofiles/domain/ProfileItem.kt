package com.example.localprofiles.domain

data class ProfileItem(
    val id: Int = UNDEFINED_ID,
    val name: String,
    val surname: String,
    val email: String,
    val dateOfBirth: String,
    val numberPhone: String,
    val description: String,
    val password: String
) {

    companion object {

        const val UNDEFINED_ID = 0
    }
}
