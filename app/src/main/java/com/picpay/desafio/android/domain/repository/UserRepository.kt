package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.core.util.Resource
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {


    suspend fun getUsers(): Flow<Resource<List<User>>>
}