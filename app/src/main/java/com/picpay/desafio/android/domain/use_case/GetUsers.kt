package com.picpay.desafio.android.domain.use_case

import com.picpay.desafio.android.core.util.Resource
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUsers(
    private val repository: UserRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<User>>> {
        return repository.getUsers()
    }

}