package com.example.localprofiles.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetProfileListUseCase @Inject constructor(
    private val profileListRepository: ProfileListRepository
) {

    operator fun invoke(): LiveData<List<ProfileItem>> {
        return profileListRepository.getProfileList()
    }
}