package com.upao.travel_trux.controllers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.upao.travel_trux.handlerSQLite.DBOpenHelper
import com.upao.travel_trux.handlerSQLite.TABLES
import com.upao.travel_trux.models.User

class UserController(context: Context) {

    private val dbHandler: DBOpenHelper = DBOpenHelper(context)
    private var db: SQLiteDatabase? = null

    private val allColumns = arrayOf(
        TABLES().USER_ID,
        TABLES().USER_NAME,
        TABLES().USER_LASTNAME,
        TABLES().USER_EMAIL,
        TABLES().USER_PASSWORD,
        TABLES().USER_PHONE,
        TABLES().USER_POINTS
    )

    fun open() {
        Log.i(LOGTAG, "Users: Database opened")
        db = dbHandler.writableDatabase
    }

    fun close() {
        Log.i(LOGTAG, "Users: Database closed")
        dbHandler.close()
    }

    fun login(email: String, password: String): Boolean {
        return true
    }

    fun register(user: User): Boolean {
        val values = ContentValues()
        values.put(TABLES().USER_NAME, user.nombre)
        values.put(TABLES().USER_LASTNAME, user.apellido)
        values.put(TABLES().USER_EMAIL, user.email)
        values.put(TABLES().USER_PASSWORD, user.password)
        values.put(TABLES().USER_PHONE, user.phone)
        values.put(TABLES().USER_POINTS, user.puntos)

        val insertId = db?.insert(TABLES().TABLE_USER, null, values)
        return insertId != -1L
    }


    fun update(user: User): Boolean {
        val values = ContentValues()
        values.put(TABLES().USER_NAME, user.nombre)
        values.put(TABLES().USER_LASTNAME, user.apellido)
        values.put(TABLES().USER_EMAIL, user.email)
        values.put(TABLES().USER_PASSWORD, user.password)
        values.put(TABLES().USER_PHONE, user.phone)
        values.put(TABLES().USER_POINTS, user.puntos)

        val updateId = db?.update(TABLES().TABLE_USER, values, "${TABLES().USER_EMAIL} = ?", arrayOf(user.email))
        return updateId != -1
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