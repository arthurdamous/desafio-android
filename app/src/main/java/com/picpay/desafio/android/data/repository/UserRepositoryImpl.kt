package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.core.util.Resource
import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.mapper.toUser
import com.picpay.desafio.android.data.mapper.toUserEntity
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class UserRepositoryImpl(
    private val picPayService: PicPayService,
    private val dao: UserDao
) : UserRepository {

    override suspend fun getUsers(): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())

        val users = dao.getUsers().map { it.toUser() }
        emit(Resource.Loading(
            data = users
        ))

        try {
            val result = picPayService.getUsers()
            val remoteUsers = result.map { it.toUserEntity() }
            dao.deleteUsers(remoteUsers.map { it.id!! })
            dao.insertUsers(remoteUsers)

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Erro na conexão, tente novamente"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Erro desconhecido, tente novamente"
                )
            )
        }
        val newRemoteUsers = dao.getUsers().map { it.toUser() }
        emit(
            Resource.Success(
                data = newRemoteUsers
            )
        )
    }
}