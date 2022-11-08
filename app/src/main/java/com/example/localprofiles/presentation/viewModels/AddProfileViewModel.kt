package com.example.localprofiles.presentation.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localprofiles.data.ProfileListRepositoryImpl
import com.example.localprofiles.domain.AddProfileItemUseCase
import com.example.localprofiles.domain.ProfileItem
import kotlinx.coroutines.launch

class AddProfileViewModel(private val application: Application) : ViewModel() {

    private val repository = ProfileListRepositoryImpl(application)

    private val addProfileItemUseCase = AddProfileItemUseCase(repository)

    fun addItemToDb(profileItem: ProfileItem) {
        viewModelScope.launch {
            addProfileItemUseCase(profileItem)
        }
    }
}