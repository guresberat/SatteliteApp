package com.guresberatcan.satteliteapp.utils

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    object Loading : Resource<Nothing>()
}