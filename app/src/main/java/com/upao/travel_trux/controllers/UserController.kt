package com.upao.travel_trux.controllers

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.upao.travel_trux.handlerSQLite.TABLES
import com.upao.travel_trux.models.User
import com.upao.travel_trux.models.requestModel.RegisterRequest
import com.upao.travel_trux.models.responseModel.RegisterResponse
import com.upao.travel_trux.service.UserService

class UserController(context: Context) {

    private val userService = UserService(context)

    fun login(context: Context, email: String, password: String, onResult: (Boolean) -> Unit) {
        userService.login(email, password) { isSuccess ->
            onResult(isSuccess)
        }
    }

    fun register(context: Context, user: RegisterRequest, onResult: (Boolean) -> Unit) {
        userService.register(context, user) { isSuccess ->
            onResult(isSuccess)
        }
    }


    fun update(user: User): Boolean {
        return true
    }

    fun delete(email: String): Boolean {
        return true
    }

    fun logout(): Boolean {
        return true
    }

    companion object {
        val LOGTAG = "DB-> "
    }
}