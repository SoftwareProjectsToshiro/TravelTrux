package com.upao.travel_trux.service

import android.content.Context
import com.upao.travel_trux.models.User
import com.upao.travel_trux.models.requestModel.LoginRequest
import com.upao.travel_trux.models.requestModel.RegisterRequest
import com.upao.travel_trux.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserService(context: Context) {

    private val userRepository = UserRepository(context)

    fun login(context: Context, user: LoginRequest, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val isSuccess = userRepository.login(context, user)
            withContext(Dispatchers.Main) {
                onResult(isSuccess)
            }
        }
    }

    fun register(context: Context, user: RegisterRequest, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val isSuccess = userRepository.register(context, user)
            withContext(Dispatchers.Main) {
                onResult(isSuccess)
            }
        }
    }

    fun getUser(context: Context, email: String, onResult: (User) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = userRepository.getUser(context, email)
            withContext(Dispatchers.Main) {
                onResult(user)
            }
        }
    }

    fun updateUser(context: Context, id: Int, user: User, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val isSuccess = userRepository.updateUser(context, id, user)
            withContext(Dispatchers.Main) {
                onResult(isSuccess)
            }
        }
    }
}