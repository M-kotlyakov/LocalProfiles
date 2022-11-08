package com.example.localprofiles.presentation.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.localprofiles.presentation.viewModels.AddProfileViewModel

class AddProfileViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddProfileViewModel::class.java)) {
            return AddProfileViewModel(application) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}