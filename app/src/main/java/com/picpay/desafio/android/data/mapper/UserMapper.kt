package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.data.remote.dto.UserDto
import com.picpay.desafio.android.domain.model.User

fun UserDto.toUser(): User {
    return User(
        img = img,
        name = name,
        id = id.toInt(),
        username = username
    )
}

fun UserDto.toUserEntity(): UserEntity{
    return UserEntity(
        id = id.toInt(),
        name = name,
        userName = username,
        imgUser = img
    )
}

fun UserEntity.toUser(): User{
    return User(
        name = name.orEmpty(),
        id = id!!,
        username = userName.orEmpty(),
        img = imgUser.orEmpty()
    )
}