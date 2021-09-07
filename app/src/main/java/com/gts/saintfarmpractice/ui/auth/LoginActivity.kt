package com.gts.saintfarmpractice.ui.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gts.saintfarmpractice.*
import com.gts.saintfarmpractice.models.User
import com.gts.saintfarmpractice.ui.BaseActivity
import com.gts.saintfarmpractice.ui.MainActivity
import com.gts.saintfarmpractice.util.Validator1
import com.gts.saintfarmpractice.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class LoginActivity : BaseActivity() {

    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    var eMail: EditText? = null
    var password:EditText? = null
    var btnlogin: Button? = null
    var registerForNewAccount: TextView? = null
    var forgotPassword: TextView? = null
    var DB: DBHelper? = null

    lateinit var arrayUsers: ArrayList<User>

    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mSharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE)
        editor = mSharedPreferences.edit()
        var isLoggedIn = mSharedPreferences.getBoolean("isLoggedIn", false)

        if(isLoggedIn){
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
            return
        }

        eMail = findViewById<View>(R.id.et_eMail_login) as EditText
        password = findViewById<View>(R.id.et_password_login) as EditText
        registerForNewAccount = findViewById<View>(R.id.tv_registerForNewAccount) as TextView
        forgotPassword = findViewById<View>(R.id.tv_forgotPassword) as TextView
        btnlogin = findViewById<View>(R.id.btn_login) as Button
        DB = DBHelper(this)

        registerForNewAccount!!.setOnClickListener {

            startActivity(Intent(this, RegisterActivity::class.java))
        }

        viewModel = ViewModelProvider(this@LoginActivity, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            UserViewModel::class.java)



        btnlogin!!.setOnClickListener{

            val eMail = eMail!!.text.toString()
            val password = password!!.text.toString()

            if (isValidInput()){

                arrayUsers = ArrayList<User>()
                viewModel.allUsers.observe(this, Observer { list ->
                    list?.let {
                        arrayUsers.addAll(it)

                        if (it.isNullOrEmpty()){
                            Toast.makeText(
                                this,
                                "User not Registered. Kindly register",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else{
                            for (itm in arrayUsers){
                                if(itm.email == eMail  && itm.password == password){
                                    viewModel.loginUser(eMail, password)
                                    editor.putString("email", eMail)
                                    editor.putString("password", password)
                                    editor.putBoolean("isLoggedIn", true);
                                    editor.apply()
                                    Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@LoginActivity, MainActivity::class.java ))
                                    finish()
                                    return@Observer
                                }else{
                                    Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                })
            }
        }
    }

    private fun isValidInput(): Boolean {
        var isValid: Boolean = true
        var message: String = ""

        message = Validator1.isValidEmail(
            this@LoginActivity,
            eMail?.text.toString().trim()
        )
        if (message != null && message.isNotEmpty()) {
            isValid = false

            tv_error_email_login.visibility = View.VISIBLE
            tv_error_email_login.text = message

            et_eMail_login.setBackgroundResource(R.drawable.dr_edittext_bg_error)

//            Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
//            return isValid
        }else{
            //Set number UI to normal
            tv_error_email_login.visibility = View.GONE
            et_eMail_login.setBackgroundResource(R.drawable.edit_text_bg)
        }

        message = Validator1.isValidNewPassword(
            this@LoginActivity,
            password?.text.toString().trim()
        )
        if (message != null && message.isNotEmpty()) {
            isValid = false

            tv_error_password_login.visibility = View.VISIBLE
            tv_error_password_login.text = message

            et_password_login.setBackgroundResource(R.drawable.dr_edittext_bg_error)

//            Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
//            return isValid
        }else{
            //Set number UI to normal
            tv_error_password_login.visibility = View.GONE
            et_password_login.setBackgroundResource(R.drawable.edit_text_bg)
        }

        return isValid
    }

}