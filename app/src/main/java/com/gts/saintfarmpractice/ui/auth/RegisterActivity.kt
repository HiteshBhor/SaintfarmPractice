package com.gts.saintfarmpractice.ui.auth


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gts.saintfarmpractice.*
import com.gts.saintfarmpractice.models.User
import com.gts.saintfarmpractice.util.Validator
import com.gts.saintfarmpractice.viewmodel.UserViewModel


class RegisterActivity : AppCompatActivity() {

    var eMail: EditText? = null
    var password: EditText? = null
    var repassword: EditText? = null
    var firstName: EditText? = null
    var lastName: EditText? = null
    var address: EditText? = null
    var register: Button? = null
    var DB: DBHelper? = null

    lateinit var arrayUsers: ArrayList<User>

    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        eMail = findViewById<View>(R.id.et_eMail) as EditText
        password = findViewById<View>(R.id.et_newPassword) as EditText
        repassword = findViewById<View>(R.id.et_confirmPassword) as EditText
        firstName = findViewById<View>(R.id.et_firstName) as EditText
        lastName = findViewById<View>(R.id.et_lastName) as EditText
        address = findViewById<View>(R.id.et_address) as EditText
        register = findViewById<View>(R.id.btn_register) as Button
        DB = DBHelper(this)

        viewModel = ViewModelProvider(this@RegisterActivity, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            UserViewModel::class.java)

        viewModel.allUsers.observe(this, Observer { list ->
            list?.let {
            }
        })

        register!!.setOnClickListener {

            val firstName = firstName!!.text.toString()
            val lastName = lastName!!.text.toString()
            val eMail = eMail!!.text.toString()
            val password = password!!.text.toString()
            val address = address!!.text.toString()

            if (isValidInput()){

                arrayUsers = ArrayList<User>()
                viewModel.allUsers.observe(this, Observer { list ->
                    list?.let {
                        arrayUsers.addAll(it)
                    }
                })
                for (itm in arrayUsers){
                    if(itm.email == eMail){
                        Toast.makeText(this@RegisterActivity, "User already exists! please sign in", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }
                viewModel.registerUser(
                    User(firstName, lastName, eMail, password, address)
                )
                Toast.makeText(this@RegisterActivity, "Registered successfully. Please Login with credentials", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java ))
                finish()

            }
        }
    }

    private fun isValidInput(): Boolean {
        var isValid: Boolean = true
        var message: String = ""

        message = Validator.isValidFirstName(
            this@RegisterActivity,
            firstName?.text.toString().trim()
        )
        if (message != null && message.isNotEmpty()) {
            isValid = false

            Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
            return isValid
        }

        message = Validator.isValidLastName(
            this@RegisterActivity,
            lastName?.text.toString().trim()
        )
        if (message != null && message.isNotEmpty()) {
            isValid = false

            Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
            return isValid
        }

        message = Validator.isValidEmail(
            this@RegisterActivity,
            eMail?.text.toString().trim()
        )
        if (message != null && message.isNotEmpty()) {
            isValid = false

            Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
            return isValid
        }

        message = Validator.isValidNewPassword(
            this@RegisterActivity,
            password?.text.toString().trim()
        )
        if (message != null && message.isNotEmpty()) {
            isValid = false

            Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
            return isValid
        }

        message = Validator.isValidConfirmPassword(
            this@RegisterActivity,
            password?.text.toString().trim(),
            repassword?.text.toString().trim(),
        )
        if (message != null && message.isNotEmpty()) {
            isValid = false

            Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
            return isValid
        }

        message = Validator.isValidAddress1(
            this@RegisterActivity,
            address?.text.toString().trim()
        )
        if (message != null && message.isNotEmpty()) {
            isValid = false

            Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
            return isValid
        }

        return isValid
    }
}