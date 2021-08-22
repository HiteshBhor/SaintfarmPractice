package com.gts.saintfarmpractice

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {

    var username: EditText? = null
    var password:EditText? = null
    var btnlogin: Button? = null
    var registerForNewAccount: TextView? = null
    var forgotPassword: TextView? = null
    var DB: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById<View>(R.id.et_eMail_login) as EditText
        password = findViewById<View>(R.id.et_password_login) as EditText
        registerForNewAccount = findViewById<View>(R.id.tv_registerForNewAccount) as TextView
        forgotPassword = findViewById<View>(R.id.tv_forgotPassword) as TextView
        btnlogin = findViewById<View>(R.id.btn_login) as Button
        DB = DBHelper(this)

        registerForNewAccount!!.setOnClickListener {

            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnlogin!!.setOnClickListener{

            val user = username!!.text.toString()
            val pass = password!!.text.toString()

            if (user == "" || pass == "") Toast.makeText(
                    this@LoginActivity,
                    "Please enter all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                    val checkuserpass = DB!!.checkusernamepassword(user, pass)
                    if (checkuserpass == true) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Sign in successfull",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Invalid Credentials",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

    }
}