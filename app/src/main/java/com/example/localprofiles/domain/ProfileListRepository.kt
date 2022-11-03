package com.example.localprofiles.domain

import androidx.lifecycle.LiveData

interface ProfileListRepository {

    suspend fun addProfileItem(profileItem: ProfileItem)

    suspend fun deleteProfileItem(profileItem: ProfileItem)

    suspend fun editProfileItem(profileItem: ProfileItem)

    suspend fun getProfileItem(profileItemID: Int): ProfileItem

    fun getProfileList(): LiveData<List<ProfileItem>>
}