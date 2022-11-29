package com.example.localprofiles.presentation

import android.app.Application
import com.example.localprofiles.di.DaggerApplicationComponent

class ProfilesApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}