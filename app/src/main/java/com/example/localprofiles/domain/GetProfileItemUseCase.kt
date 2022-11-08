package com.example.localprofiles.domain

class GetProfileItemUseCase(private val profileListRepository: ProfileListRepository) {

    suspend operator fun invoke(profileItemId: Int): ProfileItem {
        return profileListRepository.getProfileItem(profileItemId)
    }
}