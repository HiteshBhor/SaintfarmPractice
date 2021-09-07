package com.gts.saintfarmpractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.gts.saintfarmpractice.R
import com.gts.saintfarmpractice.adapters.MyOrdersVPAdapter
import kotlinx.android.synthetic.main.activity_my_orders.*
import kotlinx.android.synthetic.main.activity_products.*

class MyOrdersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)

        setupActionBar()

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)

        val adapter = MyOrdersVPAdapter(supportFragmentManager, lifecycle)

        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout,viewPager2){tab, position ->

            when(position){

                0->{
                    tab.text = "Ongoing Orders"
                }
                1->{
                    tab.text = "Order History"
                }
            }
        }.attach()

    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_my_orders)
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_btn)
            actionBar.title = "My Orders"
        }
        toolbar_my_orders.setNavigationOnClickListener{ onBackPressed() }
    }
}