package com.example.localprofiles.di

import androidx.lifecycle.ViewModel
import com.example.localprofiles.presentation.viewModels.AddProfileViewModel
import com.example.localprofiles.presentation.viewModels.AuthViewModel
import com.example.localprofiles.presentation.viewModels.HomeViewModel
import com.example.localprofiles.presentation.viewModels.PersonalAccountViewModel
import com.example.localprofiles.presentation.viewModels.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(AddProfileViewModel::class)
    @Binds
    fun bindAddProfileViewModel(impl: AddProfileViewModel): ViewModel

    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    @Binds
    fun bindAuthViewModel(impl: AuthViewModel): ViewModel

    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    @Binds
    fun bindHomeViewModel(impl: HomeViewModel): ViewModel

    @IntoMap
    @ViewModelKey(PersonalAccountViewModel::class)
    @Binds
    fun bindPersonalAccountViewModel(impl: PersonalAccountViewModel): ViewModel

    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    @Binds
    fun bindRegistrationViewModel(impl: RegistrationViewModel): ViewModel
}