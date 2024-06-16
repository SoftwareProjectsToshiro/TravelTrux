package com.upao.travel_trux.service

import android.content.Context
import com.upao.travel_trux.models.requestModel.RegisterRequest
import com.upao.travel_trux.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserService(context: Context) {

    private val userRepository = UserRepository(context)

    fun login(email: String, password: String): Boolean {
        return true
    }

    fun register(context: Context, user: RegisterRequest, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val isSuccess = userRepository.register(context, user)
            withContext(Dispatchers.Main) {
                onResult(isSuccess)
            }
        }
    }
}