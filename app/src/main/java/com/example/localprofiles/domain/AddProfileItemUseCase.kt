package com.example.localprofiles.domain

import javax.inject.Inject

class AddProfileItemUseCase @Inject constructor(
    private val profileListRepository: ProfileListRepository
) {

    suspend operator fun invoke(profileItem: ProfileItem) {
        profileListRepository.addProfileItem(profileItem)
    }
}