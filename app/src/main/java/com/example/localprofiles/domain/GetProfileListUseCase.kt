package com.example.localprofiles.domain

import androidx.lifecycle.LiveData

class GetProfileListUseCase(private val profileListRepository: ProfileListRepository) {

    operator fun invoke(): LiveData<List<ProfileItem>> {
        return profileListRepository.getProfileList()
    }
}