package com.example.localprofiles.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.localprofiles.domain.ProfileItem
import com.example.localprofiles.domain.ProfileListRepository
import javax.inject.Inject

class ProfileListRepositoryImpl @Inject constructor(
    private val profileDao: ProfileDao,
    private val mapper: ProfileListMapper
): ProfileListRepository {

    override suspend fun addProfileItem(profileItem: ProfileItem) {
        profileDao.addProfileItem(mapper.mapEntityToDbModel(profileItem))
    }

    override suspend fun deleteProfileItem(profileItem: ProfileItem) {
        profileDao.deleteProfile(profileItem.id)
    }

    override suspend fun editProfileItem(profileItem: ProfileItem) {
       profileDao.addProfileItem(mapper.mapEntityToDbModel(profileItem))
    }

    override suspend fun getUsernameByUsername(username: String, password: String): ProfileItemDbModel {
        return profileDao.getProfileByUsername(username, password)
    }

    override suspend fun getProfileItem(profileItemID: Int): ProfileItem {
        val dbModel = profileDao.getProfileItem(profileItemID)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getProfileList(): LiveData<List<ProfileItem>> = Transformations.map(
        profileDao.getProfileList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }
}