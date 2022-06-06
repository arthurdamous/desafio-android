package com.picpay.desafio.android.data.local

import androidx.room.*
import com.picpay.desafio.android.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("DELETE FROM userentity WHERE idUser IN(:usersId)")
    suspend fun deleteUsers(usersId: List<Int>)

    @Query("SELECT * FROM userentity")
    suspend fun getUsers(): List<UserEntity>
}