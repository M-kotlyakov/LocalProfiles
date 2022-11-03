package com.example.localprofiles.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.localprofiles.domain.ProfileItem
import com.example.localprofiles.domain.ProfileListRepository

class ProfileListRepositoryImpl(
    application: Application
): ProfileListRepository {

    private val profileDao = AppDataBase.getInstance(application).profileDao()

    override suspend fun addProfileItem(profileItem: ProfileItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProfileItem(profileItem: ProfileItem) {
        TODO("Not yet implemented")
    }

    override suspend fun editProfileItem(profileItem: ProfileItem) {
        TODO("Not yet implemented")
    }

    override suspend fun getProfileItem(profileItemID: Int): ProfileItem {
        TODO("Not yet implemented")
    }

    override fun getProfileList(): LiveData<List<ProfileItem>> {
        TODO("Not yet implemented")
    }
}