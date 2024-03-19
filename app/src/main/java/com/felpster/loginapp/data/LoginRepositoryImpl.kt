package com.felpster.loginapp.data

import com.felpster.loginapp.domain.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    override suspend fun authenticate(username: String, password: String): Result<Unit> {
        return if (password != "12345") {
            Result.failure(Exception("Wrong Password"))
        } else {
            Result.success(Unit)
        }
    }
}