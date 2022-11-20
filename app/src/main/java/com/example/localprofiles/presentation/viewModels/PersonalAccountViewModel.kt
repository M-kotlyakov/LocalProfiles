package com.example.localprofiles.presentation.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localprofiles.data.ProfileItemDbModel
import com.example.localprofiles.data.ProfileListMapper
import com.example.localprofiles.data.ProfileListRepositoryImpl
import com.example.localprofiles.domain.EditProfileItemUseCase
import com.example.localprofiles.domain.ProfileItem
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class PersonalAccountViewModel(
    private val application: Application
) : ViewModel() {

    private val mapper = ProfileListMapper()
    private val repository = ProfileListRepositoryImpl(application)
    private val editProfileItemUseCase = EditProfileItemUseCase(repository)

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

    private val _profileItem = MutableLiveData<ProfileItem>()
    val profileItem: LiveData<ProfileItem>
        get() = _profileItem

    fun editValues(item: ProfileItemDbModel) {
        viewModelScope.launch {
            editProfileItemUseCase(mapper.mapDbModelToEntity(item))
        }
        resetClickStatusFalse()
    }
}