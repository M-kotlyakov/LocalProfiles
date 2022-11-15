package com.example.localprofiles.presentation.viewModels

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localprofiles.data.ProfileItemDbModel
import com.example.localprofiles.data.ProfileListRepositoryImpl
import com.example.localprofiles.domain.GetProfileByUsername
import com.example.localprofiles.domain.ProfileItem
import kotlinx.coroutines.launch

class AuthViewModel(
    private val application: Application
): ViewModel() {

    var isOkay = false

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputPassword = MutableLiveData<Boolean>()
    val errorInputPassword: LiveData<Boolean>
        get() = _errorInputPassword

    private val repository = ProfileListRepositoryImpl(application)
    private val getProfileByUsername = GetProfileByUsername(repository)

    @SuppressLint("NullSafeMutableLiveData")
    fun getProfile(username: String, password: String) : LiveData<ProfileItemDbModel> {
        val item = MutableLiveData<ProfileItemDbModel>()
        val fieldsValid = validateInput(username, password)
        if (fieldsValid) {
            viewModelScope.launch {
                item.postValue(getProfileByUsername(username, password))
            }
        }
        return item
    }

    private fun validateInput(name: String, password: String) : Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if(password.isBlank()) {
            _errorInputPassword.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.postValue(false)
    }

    fun resetErrorInputPassword() {
        _errorInputPassword.postValue(false)
    }
}