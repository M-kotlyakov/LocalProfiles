package com.example.localprofiles.domain

import javax.inject.Inject

class DeleteProfileItemUseCase @Inject constructor(
    private val profileListRepository: ProfileListRepository
) {

    suspend operator fun invoke(profileItem: ProfileItem) {
        profileListRepository.deleteProfileItem(profileItem)
    }
}