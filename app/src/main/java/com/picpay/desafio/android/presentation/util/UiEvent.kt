package com.picpay.desafio.android.presentation.util

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
}