package com.example.localprofiles.di

import android.app.Application
import com.example.localprofiles.data.AppDataBase
import com.example.localprofiles.data.ProfileDao
import com.example.localprofiles.data.ProfileListRepositoryImpl
import com.example.localprofiles.domain.ProfileListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindProfileListRepository(impl: ProfileListRepositoryImpl): ProfileListRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideListDao(
            application: Application
        ): ProfileDao {
            return AppDataBase.getInstance(application).profileDao()
        }
    }
}