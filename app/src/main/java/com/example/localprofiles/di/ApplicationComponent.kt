package com.example.localprofiles.di

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.localprofiles.presentation.activities.LoginActivity
import com.example.localprofiles.presentation.activities.MainActivity
import com.example.localprofiles.presentation.fragments.AddProfileFragment
import com.example.localprofiles.presentation.fragments.AuthFragment
import com.example.localprofiles.presentation.fragments.HomeFragment
import com.example.localprofiles.presentation.fragments.PersonalAccountFragment
import com.example.localprofiles.presentation.fragments.RegistrationFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: Activity)

    fun inject(mainActivity: MainActivity)

    fun inject(loginActivity: LoginActivity)

    fun inject(fragment: Fragment)

    fun inject(authFragment: AuthFragment)

    fun inject(addProfileFragment: AddProfileFragment)

    fun inject(homeFragment: HomeFragment)

    fun inject(personalAccountFragment: PersonalAccountFragment)

    fun inject(registrationFragment: RegistrationFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}