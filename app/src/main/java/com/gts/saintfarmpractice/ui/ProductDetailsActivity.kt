package com.gts.saintfarmpractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gts.saintfarmpractice.R
import com.gts.saintfarmpractice.models.Featured
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.products_item.*

class ProductDetailsActivity : AppCompatActivity(), View.OnClickListener {

    private  var productDetail: Featured.Data.Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        var receivedString: String? = null

        llDescription.setOnClickListener(this)
        llHealthBenefits.setOnClickListener(this)

        if (intent.hasExtra("ProductDetail")) {
            receivedString = intent.getStringExtra("ProductDetail")
        }

        Log.e("ProductDetail", receivedString!!)

        val productDetailType = object : TypeToken<Featured.Data.Product?>() {}.type
        productDetail =Gson().fromJson(receivedString, productDetailType)

        Log.e("ProductValue", productDetail.toString())

        setupActionBar()
        setUI()
    }

    private fun setUI() {


        Glide.with(this@ProductDetailsActivity)
            .load("https://cdn.saintfarms.com/" + productDetail!!.product_image)
            .fitCenter()
            .into(imgProduct)

        tvProductName.text = productDetail!!.product_name

        tvDescriptionLabel.setTextColor(resources.getColor(R.color.et_heading_text_color))
        dividerDescription.setBackgroundColor(resources.getColor(R.color.et_heading_text_color))
        tvHealthBenefitsLabel.setTextColor(resources.getColor(R.color.page_heading_text_color))
        dividerHealthBenefits.setBackgroundColor(resources.getColor(R.color.strip_color))
        tvDescription.text = productDetail!!.product_description


    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_product_detail)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_btn)
            actionBar.title = "Product Detail"
        }
        toolbar_product_detail.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.llDescription -> {
                tvDescriptionLabel.setTextColor(resources.getColor(R.color.et_heading_text_color))
                dividerDescription.setBackgroundColor(resources.getColor(R.color.et_heading_text_color))
                tvHealthBenefitsLabel.setTextColor(resources.getColor(R.color.page_heading_text_color))
                dividerHealthBenefits.setBackgroundColor(resources.getColor(R.color.strip_color))
                tvDescription.text = productDetail!!.product_description
            }
            R.id.llHealthBenefits -> {
                tvDescriptionLabel.setTextColor(resources.getColor(R.color.page_heading_text_color))
                dividerDescription.setBackgroundColor(resources.getColor(R.color.strip_color))
                tvHealthBenefitsLabel.setTextColor(resources.getColor(R.color.et_heading_text_color))
                dividerHealthBenefits.setBackgroundColor(resources.getColor(R.color.et_heading_text_color))
                tvDescription.text = productDetail!!.product_health_benefits
            }
        }
    }
}