package com.example.localprofiles.domain

class EditProfileItemUseCase(private val profileListRepository: ProfileListRepository) {

    suspend operator fun invoke(profileItem: ProfileItem) {
        profileListRepository.editProfileItem(profileItem)
    }
}