package com.upao.travel_trux.controllers

import android.content.Context
import com.upao.travel_trux.models.User
import com.upao.travel_trux.models.requestModel.LoginRequest
import com.upao.travel_trux.models.requestModel.RegisterRequest
import com.upao.travel_trux.service.UserService

class UserController(context: Context) {

    private val userService = UserService(context)

    fun login(context: Context, user: LoginRequest, onResult: (Boolean) -> Unit) {
        userService.login(context, user) { isSuccess ->
            onResult(isSuccess)
        }
    }

    fun register(context: Context, user: RegisterRequest, onResult: (Boolean) -> Unit) {
        userService.register(context, user) { isSuccess ->
            onResult(isSuccess)
        }
    }

    fun getUser(context: Context, email: String, onResult: (User) -> Unit) {
        userService.getUser(context, email) { user ->
            onResult(user)
        }
    }

    fun updateUser(context: Context, id: Int, user: User, onResult: (Boolean) -> Unit) {
        userService.updateUser(context, id, user) { isSuccess ->
            onResult(isSuccess)
        }
    }

    fun delete(email: String): Boolean {
        return true
    }

    fun logout(context: Context, onResult: (Boolean) -> Unit) {
        userService.logout(context) { isSuccess ->
            onResult(isSuccess)
        }
    }

    companion object {
        val LOGTAG = "DB-> "
    }
}