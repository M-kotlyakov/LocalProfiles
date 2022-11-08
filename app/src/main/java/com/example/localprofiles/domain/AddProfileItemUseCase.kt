package com.example.localprofiles.domain

class AddProfileItemUseCase(private val profileListRepository: ProfileListRepository) {

    suspend operator fun invoke(profileItem: ProfileItem) {
        profileListRepository.addProfileItem(profileItem)
    }
}