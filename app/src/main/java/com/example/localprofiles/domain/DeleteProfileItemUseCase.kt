package com.example.localprofiles.domain

class DeleteProfileItemUseCase(private val profileListRepository: ProfileListRepository) {

    suspend operator fun invoke(profileItem: ProfileItem) {
        profileListRepository.deleteProfileItem(profileItem)
    }
}