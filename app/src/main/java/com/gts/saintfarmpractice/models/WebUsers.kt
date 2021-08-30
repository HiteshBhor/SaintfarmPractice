package com.gts.saintfarmpractice.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "WebUsers")
class WebUser(

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "first_name")
    val first_name: String,


    @ColumnInfo(name = "last_name")
    val last_name: String

    ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}