package com.felpster.loginapp.domain

interface LoginRepository {
    suspend fun authenticate(username: String, password: String) : Result<Unit>
}