package com.gts.saintfarmpractice.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.gts.saintfarmpractice.R
import com.gts.saintfarmpractice.adapters.FeaturedAdapter
import com.gts.saintfarmpractice.adapters.ShopByCategoryAdapter
import com.gts.saintfarmpractice.models.Featured
import com.gts.saintfarmpractice.models.ShopByCategory
import com.gts.saintfarmpractice.ui.auth.LoginActivity
import com.gts.saintfarmpractice.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.activity_user_list.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.featured_item.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import java.io.InputStream
import java.lang.Exception

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE)
        editor = mSharedPreferences.edit()

        var email = mSharedPreferences.getString("email", "Error")

        viewModel = ViewModelProvider(this@MainActivity, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(UserViewModel::class.java)

        setupActionBar()
        currentUserDetails(email)
        setUI()
        nav_view.setNavigationItemSelectedListener(this)

    }

    private fun currentUserDetails(email: String?) {
        viewModel.checkEmail(email!!).observe(this, {

            var firstName = it.firstName
            var lastName = it.lastName
            var address = it.address

            editor.putString("firstName", firstName)
            editor.putString("lastName", lastName)
            editor.putString("address", address)
            editor.apply()

            tv_username.text = mSharedPreferences.getString("firstName", "Error")
            tv_eMail.text = mSharedPreferences.getString("email", "Error")
            tv_currentUser.text = mSharedPreferences.getString("firstName", "Error")
        })
    }

    private fun setupActionBar(){

        setSupportActionBar(toolbar_main_activity)
        toolbar_main_activity.setNavigationIcon(R.drawable.ic_action_navigation_menu)
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.title = "Dashboard"
        }
        toolbar_main_activity.setNavigationOnClickListener {
            toggleDrawer()
        }

    }

    private fun toggleDrawer(){
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else{
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else{
            doubleBackToExit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_my_profile ->{
               startActivity(Intent(this@MainActivity, MyProfileActivity::class.java ))
            }

            R.id.nav_my_orders ->{
                startActivity(Intent(this@MainActivity, MyOrdersActivity::class.java ))
            }

            R.id.nav_user_list ->{
                startActivity(Intent(this@MainActivity, UserListActivity::class.java ))
            }

            R.id.nav_sign_out ->{

                editor.putString("email", "")
                editor.putString("password", "")
                editor.putString("firstName", "")
                editor.putString("lastName", "")
                editor.putString("address", "")
                editor.putBoolean("isLoggedIn", false)
                editor.apply()

                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("finish", true)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }

    private fun loadJson(context: Context, isCategory: Boolean): String? {
        var input: InputStream? = null
        var jsonString: String

        try {
            //Create InputStream
                if (isCategory){
                    input = context.assets.open("shopByCategory.json")
                }else{
                    input = context.assets.open("featured.json")
                }

            val size = input.available()

            //Create buffer with size
            val buffer = ByteArray(size)

            //Read data from InputStream into the Buffer
            input.read(buffer)

            //Create Json String
            jsonString = String(buffer)
            return jsonString

        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            //Must close the stream
            input?.close()
        }
        return null
    }

    private fun setUI() {

        val jsonStringFeatured = loadJson(this, false)
        val jsonStringShopByCategory = loadJson(this, true)

        //Using Gson to parse json string
        val featuredProducts = Gson().fromJson(jsonStringFeatured, Featured::class.java)
        val shopByCategory = Gson().fromJson(jsonStringShopByCategory, ShopByCategory::class.java)

        Log.d("MainActivity", "featuredProductsSize: ${featuredProducts.data.size}")
        Log.d("MainActivity", "ShopByCategorySize: ${shopByCategory.data.size}")

        //featured list
        val featuredAdapter = FeaturedAdapter(this, featuredProducts.data)
        rv_featured.adapter = featuredAdapter

        featuredAdapter.setOnClickListener(object : FeaturedAdapter.OnClickListener{
            override fun onClick(position: Int, model: List<Featured.Data.Product>) {

                val intent = Intent(this@MainActivity, ProductsActivity::class.java)
//                intent.putExtra("ProductList", model.toString())
                intent.putExtra("ProductList", Gson().toJson(model))
                startActivity(intent)
//                Toast.makeText(this@MainActivity, model.get(0).product_name, Toast.LENGTH_SHORT).show()
            }
        })

        //shopByCategory list
        rv_shopByCategory.layoutManager = GridLayoutManager(this@MainActivity,3, GridLayoutManager.VERTICAL, false)//{ override fun canScrollVertically(): Boolean { return false } }
        rv_shopByCategory.suppressLayout(true)

        val shopByCategoryAdapter = ShopByCategoryAdapter(this, shopByCategory.data)
        rv_shopByCategory.adapter = shopByCategoryAdapter
    }
}