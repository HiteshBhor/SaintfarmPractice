package com.gts.saintfarmpractice


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class RegisterActivity : AppCompatActivity() {

    var username: EditText? = null
    var password:EditText? = null
    var repassword:EditText? = null
    var firstName:EditText? = null
    var lastName:EditText? = null
    var address:EditText? = null
    var register: Button? = null
    var DB: DBHelper? = null

    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        username = findViewById<View>(R.id.et_eMail) as EditText
        password = findViewById<View>(R.id.et_newPassword) as EditText
        repassword = findViewById<View>(R.id.et_confirmPassword) as EditText
        firstName = findViewById<View>(R.id.et_firstName) as EditText
        lastName = findViewById<View>(R.id.et_lastName) as EditText
        address = findViewById<View>(R.id.et_address) as EditText
        register = findViewById<View>(R.id.btn_register) as Button
        DB = DBHelper(this)

        viewModel = ViewModelProvider(this@RegisterActivity, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(UserViewModel::class.java)

        viewModel.allUsers.observe(this, Observer { list ->
            list?.let {
            }
        })

        register!!.setOnClickListener {
                val user = username!!.text.toString()
                val pass = password!!.text.toString()
                val repass = repassword!!.text.toString()
                val firstName = repassword!!.text.toString()
                val lastName = repassword!!.text.toString()
                val address = repassword!!.text.toString()

                if (user == "" || pass == "" || repass == "" || firstName == "" || lastName == "" || address == "")
                    Toast.makeText(
                    this@RegisterActivity,
                    "Please enter all the fields",
                    Toast.LENGTH_SHORT
                    ).show()

                else {

                    viewModel.registerUser(User(firstName, lastName, user, pass, address ))
                    Toast.makeText(this, "User $firstName Inserted Successfully", Toast.LENGTH_SHORT).show()


//                    if (pass == repass) {
//                        val checkuser = DB!!.checkusername(user)
//                        if (checkuser == false) {
//                            val insert = DB!!.insertData(user, pass, firstName, lastName, address)
//                            if (insert == true) {
//                                Toast.makeText(
//                                    this@RegisterActivity,
//                                    "Registered successfully. Please Login with credentials",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                val intent = Intent(applicationContext, LoginActivity::class.java)
//                                startActivity(intent)
//                            } else {
//                                Toast.makeText(
//                                    this@RegisterActivity,
//                                    "Registration failed",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        } else {
//                            Toast.makeText(
//                                this@RegisterActivity,
//                                "User already exists! please sign in",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    } else {
//                        Toast.makeText(
//                            this@RegisterActivity,
//                            "Passwords not matching",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
                }
        }
    }
}