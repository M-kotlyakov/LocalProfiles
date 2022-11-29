package com.example.localprofiles.domain

import com.example.localprofiles.data.ProfileItemDbModel
import javax.inject.Inject

class GetProfileByUsername @Inject constructor(
    private val profileListRepository: ProfileListRepository
) {

    suspend operator fun invoke(username: String, password: String): ProfileItemDbModel {
        return profileListRepository.getUsernameByUsername(username, password)
    }
}