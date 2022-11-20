package com.example.localprofiles.presentation.viewModels

import android.app.Application
import android.graphics.Bitmap
import android.media.Image
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localprofiles.data.ProfileListRepositoryImpl
import com.example.localprofiles.domain.AddProfileItemUseCase
import com.example.localprofiles.domain.ProfileItem
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class AddProfileViewModel(private val application: Application) : ViewModel() {

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

    private var isChoose = false

    fun checkEmail(email: String): Boolean = emailFormatted.matcher(email).matches()

    fun resetClickStatusTrue() {
        isChoose = true
    }

    fun resetClickStatusFalse() {
        isChoose = false
    }

    fun statusOfChoose() = isChoose

    fun addItemToDb(
        image: Bitmap? = null,
        name: String,
        surname: String,
        email: String,
        dateOfBirth: String,
        description: String
    ) {
        Log.d("AddProfileViewModel", "$image")
        viewModelScope.launch {
            val item = ProfileItem(
                image = image,
                name = name,
                surname = surname,
                email = email,
                dateOfBirth = dateOfBirth,
                description = description,
                numberPhone = "8925552525",
                password = "111"
            )
            addProfileItemUseCase(item)
            resetClickStatusFalse()
        }
    }
}