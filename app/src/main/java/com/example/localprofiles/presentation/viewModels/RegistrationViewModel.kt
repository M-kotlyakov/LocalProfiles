package com.example.localprofiles.presentation.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.localprofiles.data.ProfileListRepositoryImpl
import com.example.localprofiles.domain.AddProfileItemUseCase
import com.example.localprofiles.domain.ProfileItem
import com.example.localprofiles.presentation.factory.ViewModelFactory
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegistrationViewModel(
    private val application: Application
): ViewModel() {

    private val repository = ProfileListRepositoryImpl(application)
    private val addProfileItemUseCase = AddProfileItemUseCase(repository)

    private val emailFormatted: Pattern
        get() = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+")


    fun checkEmail(email: String): Boolean = emailFormatted.matcher(email).matches()

    private val _isSuccess = MutableLiveData<Boolean>(false)
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    private val _errorInputPassword = MutableLiveData<Boolean>()
    val errorInputPassword: LiveData<Boolean>
        get() = _errorInputPassword

    private val _errorInputRePassword = MutableLiveData<Boolean>()
    val errorInputRePassword: LiveData<Boolean>
        get() = _errorInputRePassword

    private val _errorInputName= MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputEmail = MutableLiveData<Boolean>()
    val errorInputEmail: LiveData<Boolean>
        get() = _errorInputEmail

    fun registrationProfile(
        inputName: String,
        inputEmail : String,
        inputPassword: String,
        inputRePassword: String
    ) {
        val name = parseName(inputName)
        val email = parseName(inputEmail)
        val password = parseName(inputPassword)
        val rePassword = parseName(inputRePassword)

        if (
            validateInput(
                name,
                email,
                password,
                rePassword
            )
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
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?) : String {
        return inputName?.trim() ?: ""
    }

    private fun validateInput(
        name: String,
        email : String,
        password: String,
        rePassword: String
    ): Boolean {
        var result = true
        if(password.isBlank() || password.length < 6) {
            _errorInputPassword.postValue(true)
            result = false
        }
        if(rePassword.isBlank() || password.length < 6) {
            _errorInputRePassword.postValue(true)
            result = false
        }
        if(name.isBlank()) {
            _errorInputName.postValue(true)
            result = false
        }
        if(email.isBlank()) {
            _errorInputEmail.postValue(true)
            result = false
        }
        return result
    }

    fun resetErrorInputPassword() {
        _errorInputPassword.postValue(false)
    }

    fun resetErrorInputRePassword() {
        _errorInputRePassword.postValue(false)
    }

    fun resetErrorInputName() {
        _errorInputName.postValue(false)
    }

    fun resetErrorInputEmail() {
        _errorInputEmail.postValue(false)
    }

    private fun finishWork() {
        _isSuccess.postValue(true)
    }
}