package com.picpay.desafio.android.presentation

import com.picpay.desafio.android.domain.model.User

data class MainActivityState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false
)
