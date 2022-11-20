package com.example.localprofiles.data

import com.example.localprofiles.domain.ProfileItem

class ProfileListMapper {

    fun mapEntityToDbModel(profileItem: ProfileItem) = ProfileItemDbModel(
        id = profileItem.id,
        image = profileItem.image,
        name = profileItem.name,
        surname = profileItem.surname,
        email = profileItem.email,
        dateOfBirth = profileItem.dateOfBirth,
        numberPhone = profileItem.numberPhone,
        description = profileItem.description,
        password = profileItem.password
    )

    fun mapDbModelToEntity(profileItemDbModel: ProfileItemDbModel) = ProfileItem(
        id = profileItemDbModel.id,
        image = profileItemDbModel.image,
        name = profileItemDbModel.name,
        surname = profileItemDbModel.surname,
        email = profileItemDbModel.email,
        dateOfBirth = profileItemDbModel.dateOfBirth,
        numberPhone = profileItemDbModel.numberPhone,
        description = profileItemDbModel.description,
        password = profileItemDbModel.password
    )

    fun mapListDbModelToListEntity(list: List<ProfileItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }

    fun mapListEntityToListDbModel(list: List<ProfileItem>) = list.map {
        mapEntityToDbModel(it)
    }
}