package com.example.localprofiles.presentation.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localprofiles.data.ProfileListRepositoryImpl
import com.example.localprofiles.domain.AddProfileItemUseCase
import com.example.localprofiles.domain.ProfileItem
import kotlinx.coroutines.launch

class AddProfileViewModel(private val application: Application) : ViewModel() {

    private val repository = ProfileListRepositoryImpl(application)
    private val addProfileItemUseCase = AddProfileItemUseCase(repository)

    fun addItemToDb(
        name: String,
        surname: String,
        email : String,
        dateOfBirth: String,
        description: String
    ) {
        viewModelScope.launch {
            val item = ProfileItem(
                name = name,
                surname = surname,
                email = email,
                dateOfBirth = dateOfBirth,
                description = description,
                numberPhone = "8925552525",
                password = "111"
            )
            addProfileItemUseCase(item)
        }
    }
}