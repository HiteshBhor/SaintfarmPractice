package com.gts.saintfarmpractice.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gts.saintfarmpractice.R
import com.gts.saintfarmpractice.adapters.ProductsAdapter
import com.gts.saintfarmpractice.models.Featured
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.content_main.*


class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        setupActionBar()
        setProductList()
    }

    private fun setProductList() {
        var receivedString: String? = null

        if (intent.hasExtra("ProductList")){
            receivedString = intent.getStringExtra("ProductList")
        }

        Log.e("PRODUCTS", receivedString!!)

        val productListType = object : TypeToken<List<Featured.Data.Product?>?>() {}.type
        val productArray: ArrayList<Featured.Data.Product> = Gson().fromJson(receivedString, productListType)


        val productsAdapter = ProductsAdapter(this, productArray)
        rv_products.adapter = productsAdapter

        productsAdapter.setOnClickListener(object : ProductsAdapter.OnClickListener {
            override fun onClick(position: Int, model: Featured.Data.Product) {

//                Toast.makeText(this@ProductsActivity, "$model", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@ProductsActivity, ProductDetailsActivity::class.java)
                intent.putExtra("ProductDetail", Gson().toJson(model))
                startActivity(intent)
            }
        })

//        val myType = object : TypeToken<List<Featured.Data.Product>>() {}.type
//        val logs = Gson().fromJson<List<Featured.Data.Product>>(productListString, myType)
//



//        try {
//            // JSON array
//                productListString
//
//            // convert JSON array to Java List
//            val products =
//                Gson().fromJson<List<Featured.Data.Product>>(productListString, object : TypeToken<List<Featured.Data.Product?>?>() {}.type)
//
//            Toast.makeText(this@ProductsActivity, "$products", Toast.LENGTH_SHORT).show()
//
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//        }
//        val productsAdapter = ProductsAdapter(this, products = List<Featured.Data.Product>())
//        rv_products.adapter = productsAdapter
    }


    private fun setupActionBar() {
        setSupportActionBar(toolbar_products_activity)
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_btn)
            actionBar.title = "Products"
        }
        toolbar_products_activity.setNavigationOnClickListener{ onBackPressed() }
    }
}