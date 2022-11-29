package com.example.localprofiles.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localprofiles.data.ProfileListRepositoryImpl
import com.example.localprofiles.domain.DeleteProfileItemUseCase
import com.example.localprofiles.domain.ProfileItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: ProfileListRepositoryImpl,
    private val deleteProfileItemUseCase: DeleteProfileItemUseCase
) : ViewModel() {

    val getList = repository.getProfileList()

    fun deleteProfile(profileItem: ProfileItem) {
        viewModelScope.launch {
            deleteProfileItemUseCase(profileItem)
        }
    }
}