package com.example.localprofiles.domain

import androidx.lifecycle.LiveData
import com.example.localprofiles.data.ProfileItemDbModel

interface ProfileListRepository {

    suspend fun addProfileItem(profileItem: ProfileItem)

    suspend fun deleteProfileItem(profileItem: ProfileItem)

    suspend fun editProfileItem(profileItem: ProfileItem)

    suspend fun getProfileItem(profileItemID: Int): ProfileItem

    suspend fun getUsernameByUsername(username: String, password: String): ProfileItemDbModel

    fun getProfileList(): LiveData<List<ProfileItem>>
}