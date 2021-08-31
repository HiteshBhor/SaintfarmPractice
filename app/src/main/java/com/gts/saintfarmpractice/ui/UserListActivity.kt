package com.gts.saintfarmpractice.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gts.saintfarmpractice.APiState
import com.gts.saintfarmpractice.R
import com.gts.saintfarmpractice.adapters.UserListAdapter
import com.gts.saintfarmpractice.models.WebUser
import com.gts.saintfarmpractice.repositories.UserListRepository
import com.gts.saintfarmpractice.viewmodel.UserListViewModel
import com.gts.saintfarmpractice.viewmodel.UserViewModel
import com.gts.saintfarmpractice.webservices.RetrofitService
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.activity_user_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class UserListActivity : AppCompatActivity() {

    lateinit var viewModel: UserListViewModel
    lateinit var webUserViewModel: UserViewModel

    private val retrofitService = RetrofitService.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        setupActionBar()

        viewModel = UserListViewModel(UserListRepository(retrofitService))

        webUserViewModel =  ViewModelProvider(this@UserListActivity, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            UserViewModel::class.java)

        initObserver()


        if (isNetworkAvailable(this)){

            fetchUserList()
            webUserViewModel.allWebUsers.observe(this, { list ->
                list?.let {
                    recyclerviewUsers.layoutManager =LinearLayoutManager(this)
                    val adapter = UserListAdapter(this, it as ArrayList<WebUser>)
                    recyclerviewUsers.adapter = adapter
                    adapter.updateList(it)
                }

            })

        }else{

            Toast.makeText(this@UserListActivity, "Fetched from local DB", Toast.LENGTH_SHORT).show()

            webUserViewModel.allWebUsers.observe(this, { list ->
                list?.let {
                    recyclerviewUsers.layoutManager =LinearLayoutManager(this)
                    val adapter = UserListAdapter(this, it as ArrayList<WebUser>)
                    recyclerviewUsers.adapter = adapter
                    adapter.updateList(it)
                }

            })

        }

//        recyclerviewUsers.layoutManager =LinearLayoutManager(this)
//        val adapter = UserListAdapter(this, )
//        recyclerviewUsers.adapter = adapter


    }

    private fun fetchUserList() {
        viewModel.getAllUsers()
    }

    private fun initObserver() {
        viewModel.userListHoldLiveData.observe(
            this, Observer { state ->
                when (state) {
                  is APiState.Loading -> {
                        Toast.makeText(this@UserListActivity ,"API Loading", Toast.LENGTH_LONG).show()
                    }
                    is APiState.Error -> {
                        Toast.makeText(this@UserListActivity ,"API Error", Toast.LENGTH_LONG).show()
                    }
                    is APiState.Success -> {
                        Toast.makeText(this@UserListActivity ,"API Success", Toast.LENGTH_LONG).show()
                        val data = state.data
                        Log.e("USERLIST", "$data.data")
                        /*val adapter = UserListAdapter(this, data.data)
                        recyclerviewUsers.adapter = adapter*/

                        webUserViewModel.deleteAllWebUsers()

                        for (itm in data.data){

                            webUserViewModel.insertWebUser(WebUser(itm.avatar, itm.email, itm.first_name, itm.last_name))
                        }
                        Toast.makeText(this@UserListActivity ,"Web User list saved successfully ", Toast.LENGTH_LONG).show()
                    }
                }
            }
        )
    }

    fun isNetworkAvailable(context: Context): Boolean {
        // It answers the queries about the state of network connectivity.
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network      = connectivityManager.activeNetwork ?: return false
            val activeNetWork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetWork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetWork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                activeNetWork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            // Returns details about the currently active default data network.
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }

    private fun setupActionBar(){
        setSupportActionBar(toolbar_user_list_activity)
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_btn)
            actionBar.title = "User List"
        }
        toolbar_user_list_activity.setNavigationOnClickListener{ onBackPressed() }
    }

}