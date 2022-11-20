package com.example.localprofiles.presentation.activities

import androidx.databinding.BindingAdapter
import com.example.localprofiles.R
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorInputUserName")
fun bindErrorInputUserName(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.error_input_username)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("errorInputEmail")
fun bindErrorInputUserEmail(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.error_input_email)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("errorInputPassword")
fun bindErrorInputPassword(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.error_input_password)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("errorInputRePassword")
fun bindErrorInputRePassword(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.error_input_rePassword)
    } else {
        null
    }
    textInputLayout.error = message
}