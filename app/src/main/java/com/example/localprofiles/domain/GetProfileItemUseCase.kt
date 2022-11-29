package com.example.localprofiles.domain

import javax.inject.Inject

class GetProfileItemUseCase @Inject constructor(
    private val profileListRepository: ProfileListRepository
) {

    suspend operator fun invoke(profileItemId: Int): ProfileItem {
        return profileListRepository.getProfileItem(profileItemId)
    }
}