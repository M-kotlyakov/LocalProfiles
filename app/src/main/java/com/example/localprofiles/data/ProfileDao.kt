package com.example.localprofiles.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile_item")
    fun getProfileList(): LiveData<List<ProfileItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProfileItem(profileItemDbModel: ProfileItemDbModel)

    @Query("DELETE FROM profile_item WHERE id =:profileItemId")
    suspend fun deleteProfile(profileItemId: Int)

    @Query("SELECT * FROM PROFILE_ITEM WHERE id =:profileItemId LIMIT 1")
    suspend fun getProfileItem(profileItemId: Int): ProfileItemDbModel
}