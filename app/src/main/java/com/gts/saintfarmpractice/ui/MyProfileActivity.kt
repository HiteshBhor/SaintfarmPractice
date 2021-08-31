package com.gts.saintfarmpractice.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.gts.saintfarmpractice.R
import com.gts.saintfarmpractice.models.User
import com.gts.saintfarmpractice.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_my_profile.*

class  MyProfileActivity : AppCompatActivity() {

    lateinit var arrayUsers: ArrayList<User>

    lateinit var viewModel: UserViewModel

    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        setupActionBar()

        mSharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE)
        var email = mSharedPreferences.getString("email", "Error")

        viewModel = ViewModelProvider(this@MyProfileActivity, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            UserViewModel::class.java)
        
        
        viewModel.checkEmail(email!!).observe(this, {

            et_firstName.setText(it.firstName)
            et_lastName.setText(it.lastName)
            et_eMail.setText(email)
            et_password.setText(it.password)
            et_address.setText(it.address)
        })

        btn_Update.setOnClickListener {
            val firstName = et_firstName.text.toString()
            val lastName = et_lastName.text.toString()
            val password = et_password.text.toString()
            val address = et_address.text.toString()
            if(firstName.isNotEmpty() && lastName.isNotEmpty()
                && password.isNotEmpty() && address.isNotEmpty()){
                this.updateUserProfile1(firstName, lastName, password, address, email)
            }
        }

//        arrayUsers = ArrayList<User>()
//        viewModel.allUsers.observe(this, Observer { list ->
//            list?.let {
//                arrayUsers.addAll(it)
//
//                for (itm in arrayUsers){
//                    if (itm.email == email){
//
//                    }
//                }
//
//            }
//        })
    }

    private fun updateUserProfile1(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        address: String,
    ) {
        /*val user: HashMap<String, Any> = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "password" to password,
            "address" to address,
        )*/

        viewModel.updateUserProfile(User(firstName,lastName,email,password, address))
        Toast.makeText(this@MyProfileActivity, "Profile Updated Successfully", Toast.LENGTH_SHORT).show()
    }

    private fun setupActionBar(){
        setSupportActionBar(toolbar_my_profile_activity)
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_btn)
            actionBar.title = "My Profile"
        }
        toolbar_my_profile_activity.setNavigationOnClickListener{ onBackPressed() }
    }
}