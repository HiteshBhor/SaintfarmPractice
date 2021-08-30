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

        viewModel = UserListViewModel(UserListRepository(retrofitService))

        webUserViewModel =  ViewModelProvider(this@UserListActivity, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            UserViewModel::class.java)

        initObserver()

        fetchUserList()

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

                        for (itm in data.data){

                            webUserViewModel.insertWebUser(WebUser(itm.avatar, itm.email, itm.first_name, itm.last_name))
                        }
                        Toast.makeText(this@UserListActivity ,"Web User list saved successfully ", Toast.LENGTH_LONG).show()
                        webUserViewModel.allWebUsers.observe(this, { list ->
                            list?.let {
                                recyclerviewUsers.layoutManager =LinearLayoutManager(this)
                                val adapter = UserListAdapter(this, it as ArrayList<WebUser>)
                                recyclerviewUsers.adapter = adapter
                                adapter.updateList(it)
                            }

                        })
                    }
                }
            }
        )
    }
}