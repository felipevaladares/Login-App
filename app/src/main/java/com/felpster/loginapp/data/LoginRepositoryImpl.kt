package com.felpster.loginapp.data

import com.felpster.loginapp.domain.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    override suspend fun authenticate(username: String, password: String): Result<Unit> {
        return if (password != "12345") {
            Result.failure(Exception("Invalid Password!!"))
        } else {
            Result.success(Unit)
        }
    }
}