package com.upao.travel_trux.repositories

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.upao.travel_trux.data.api.Apiclient
import com.upao.travel_trux.data.endpoints.ApiService
import com.upao.travel_trux.helpers.SharedPreferencesManager
import com.upao.travel_trux.models.ApiError
import com.upao.travel_trux.models.User
import com.upao.travel_trux.models.requestModel.LoginRequest
import com.upao.travel_trux.models.requestModel.RegisterRequest
import com.upao.travel_trux.models.responseModel.ErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

class UserRepository(context: Context) {

    suspend fun register(context: Context, user: RegisterRequest) : Boolean{
        val apiService = Apiclient.createService(ApiService::class.java)
        val response = apiService.register(user)
        return withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                val registerResponse = response.body()
                Toast.makeText(context, registerResponse?.msg, Toast.LENGTH_SHORT).show()
                true
            } else {
                val errorResponse = response.errorBody()?.string()
                val apiErrors = parseError(errorResponse)
                apiErrors?.let { errors ->
                    for (error in errors) {
                        val capitalizedCode = error.code.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }
                        Toast.makeText(
                            context,
                            "${capitalizedCode}: ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } ?: run {
                    Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show()
                }
                false
            }
        }
    }

    suspend fun login(context: Context, user: LoginRequest): Boolean {
        val apiService = Apiclient.createService(ApiService::class.java)
        val response = apiService.login(user)
        return withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                val loginResponse = response.body()
                val email = loginResponse?.user?.email
                loginResponse?.token.let {
                    SharedPreferencesManager.setToken(context, it!!)
                }
                val saveUser = "${user.email},${user.password}"
                SharedPreferencesManager.setUserData(context, saveUser)
                Toast.makeText(context,"Bienvenido $email" , Toast.LENGTH_SHORT).show()
                true
            } else {
                val errorResponse = response.errorBody()?.string()
                val apiErrors = parseError(errorResponse)
                apiErrors?.let { errors ->
                    for (error in errors) {
                        val capitalizedCode = error.code.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }
                        Toast.makeText(
                            context,
                            "${capitalizedCode}: ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } ?: run {
                    Toast.makeText(context, "Inicie Sesión o Registrese", Toast.LENGTH_SHORT).show()
                }
                false
            }
        }
    }

    suspend fun getUser(context: Context, email: String): User {
        val apiService = Apiclient.createService(ApiService::class.java)
        val response = apiService.userGet(email)
        return withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                val data = response.body()
                if(data != null) {
                    val user = User(
                                id=data.id,
                                nombre=data.nombre,
                                apellido= data.apellido,
                                email=data.email,
                                phone=data.phone
                            )
                    user
                } else {
                    User(0,"","","","")
                }
            } else {
                val errorResponse = response.errorBody()?.string()
                val apiErrors = parseError(errorResponse)
                apiErrors?.let { errors ->
                    for (error in errors) {
                        val capitalizedCode = error.code.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }
                        Toast.makeText(
                            context,
                            "${capitalizedCode}: ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } ?: run {
                    Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show()
                }
                User(0,"","","","")
            }
        }
    }

    suspend fun updateUser(context: Context, id: Int, user:User): Boolean {
        val apiService = Apiclient.createService(ApiService::class.java)
        val response = apiService.userUpdate(id, user)
        return withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                Toast.makeText(context, "Email Actualizado: \n${user.email}", Toast.LENGTH_SHORT).show()
                true
            } else {
                val errorResponse = response.errorBody()?.string()
                val apiErrors = parseError(errorResponse)
                apiErrors?.let { errors ->
                    for (error in errors) {
                        val capitalizedCode = error.code.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }
                        Toast.makeText(
                            context,
                            "${capitalizedCode}: ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } ?: run {
                    Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show()
                }
                false
            }
        }
    }

    suspend fun logout(context: Context): Boolean {
        return withContext(Dispatchers.Main) {
            SharedPreferencesManager.removeUserData(context)
            SharedPreferencesManager.removeToken(context)
            println("Sign out")
            true
        }
    }

    private fun parseError(errorBody: String?): List<ApiError>? {
        return try {
            errorBody?.let {
                val gson = Gson()
                val errorResponse = gson.fromJson(it, ErrorResponse::class.java)
                errorResponse.errors
            }
        } catch (e: Exception) {
            null
        }
    }
}