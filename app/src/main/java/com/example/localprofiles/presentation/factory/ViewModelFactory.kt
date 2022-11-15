package com.example.localprofiles.presentation.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.localprofiles.presentation.viewModels.AddProfileViewModel
import com.example.localprofiles.presentation.viewModels.HomeViewModel
import com.example.localprofiles.presentation.viewModels.AuthViewModel
import com.example.localprofiles.presentation.viewModels.PersonalAccountViewModel
import com.example.localprofiles.presentation.viewModels.RegistrationViewModel

class ViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddProfileViewModel::class.java)) {
            return AddProfileViewModel(application) as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(application) as T
        }
        if (modelClass.isAssignableFrom(PersonalAccountViewModel::class.java)) {
            return PersonalAccountViewModel(application) as T
        }
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(application) as T
        }
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(application) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}