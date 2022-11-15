package com.example.localprofiles.presentation.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localprofiles.data.ProfileListRepositoryImpl
import com.example.localprofiles.domain.DeleteProfileItemUseCase
import com.example.localprofiles.domain.ProfileItem
import kotlinx.coroutines.launch

class HomeViewModel(
    private val application: Application
) : ViewModel() {

    private val repository = ProfileListRepositoryImpl(application)
    private val deleteProfileItemUseCase = DeleteProfileItemUseCase(repository)

    val getList = repository.getProfileList()

    fun deleteProfile(profileItem: ProfileItem) {
        viewModelScope.launch {
            deleteProfileItemUseCase(profileItem)
        }
    }
}