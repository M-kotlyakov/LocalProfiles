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
import java.util.regex.Pattern
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val getProfileByUsername: GetProfileByUsername
): ViewModel() {

    private var isSuccess = false

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputPassword = MutableLiveData<Boolean>()
    val errorInputPassword: LiveData<Boolean>
        get() = _errorInputPassword

    @SuppressLint("NullSafeMutableLiveData")
    fun getProfile(username: String, password: String) : LiveData<ProfileItemDbModel> {
        val item = MutableLiveData<ProfileItemDbModel>()
        val fieldsValid = validateInput(username, password)
        if (fieldsValid) {
            viewModelScope.launch {
                item.postValue(getProfileByUsername(username, password))
                finishWork()
            }
        }
        return item
    }

    private fun validateInput(inputName: String, inputPassword: String) : Boolean {
        var result = true
        val name = parseName(inputName)
        val password = parseName(inputPassword)
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

    private fun parseName(inputName: String?) : String {
        return inputName?.trim() ?: ""
    }

    fun resetErrorInputName() {
        _errorInputName.postValue(false)
    }

    fun resetErrorInputPassword() {
        _errorInputPassword.postValue(false)
    }

    private fun finishWork() {
        isSuccess = true
    }
}