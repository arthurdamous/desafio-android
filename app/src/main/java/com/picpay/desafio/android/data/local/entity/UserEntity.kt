package com.picpay.desafio.android.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val idUser: Int? = null,
    val userName: String? = null,
    val name: String? = null,
    val imgUser: String? = null,
    val id: Int? = null
)
