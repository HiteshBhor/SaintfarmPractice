package com.gts.saintfarmpractice

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, "UserData.db", null, 1) {
    override fun onCreate(MyDB: SQLiteDatabase) {
        MyDB.execSQL("create Table users(username TEXT primary key, " +
                "password TEXT, firstName TEXT, lastName TEXT, address TEXT )")
    }

    override fun onUpgrade(MyDB: SQLiteDatabase, i: Int, i1: Int) {
        MyDB.execSQL("drop Table if exists users")
    }

    fun insertData(username: String?, password: String?, firstName: String?, lastName: String?, address: String?): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        contentValues.put("firstName", password)
        contentValues.put("lastName", password)
        contentValues.put("address", password)
        val result = MyDB.insert("users", null, contentValues)
        return result != -1L
    }

    fun checkusername(username: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor: Cursor =
            MyDB.rawQuery("Select * from users where username = ?", arrayOf(username))
        return cursor.count > 0
    }

    fun checkusernamepassword(username: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor: Cursor = MyDB.rawQuery(
            "Select * from users where username = ? and password = ?",
            arrayOf(username, password)
        )
        return cursor.count > 0
    }

    companion object {
        const val DBNAME = "Login.db"
    }
}