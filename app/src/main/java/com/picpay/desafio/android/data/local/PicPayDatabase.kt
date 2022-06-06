package com.picpay.desafio.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.local.entity.UserEntity

@Database(
    version = 1,
    entities = [
        UserEntity::class
    ]
)
abstract class PicPayDatabase : RoomDatabase() {

    abstract val userDao: UserDao
}