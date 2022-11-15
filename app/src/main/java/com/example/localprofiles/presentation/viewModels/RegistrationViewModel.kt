package com.example.localprofiles.presentation.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.localprofiles.data.ProfileListRepositoryImpl
import com.example.localprofiles.domain.AddProfileItemUseCase
import com.example.localprofiles.domain.ProfileItem
import com.example.localprofiles.presentation.factory.ViewModelFactory
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val application: Application
): ViewModel() {

    private val repository = ProfileListRepositoryImpl(application)
    private val addProfileItemUseCase = AddProfileItemUseCase(repository)

    fun registrationProfile(
        name: String,
        email : String,
        password: String,
        rePassword: String
    ) {
        viewModelScope.launch {
            val item = ProfileItem(
                name = name,
                surname = "",
                email = email,
                dateOfBirth = "",
                description = "",
                numberPhone = "",
                password = password
            )
            addProfileItemUseCase(item)
        }
    }
}