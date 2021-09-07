package com.gts.saintfarmpractice.models

data class ShopByCategory(
    var `data`: List<Data>,
    var error: Any,
    var message: String,
    var no_product_message: String,
    var status: String
) {
    data class Data(
        var cat_id: Int,
        var cat_order: Int,
        var created_at: String,
        var description: String,
        var featured_banner: String,
        var featured_cat_order: Int,
        var image: String,
        var is_featured: String,
        var level: Int,
        var parent: Int,
        var product: List<Product>,
        var show_in_list: String,
        var slug: String,
        var status: Int,
        var title: String,
        var updated_at: String
    ) {
        data class Product(
            var cat_id: Int,
            var coming_soon: String,
            var created_at: String,
            var is_active: String,
            var out_of_stock: String,
            var prod_order: Int,
            var product_description: String,
            var product_health_benefits: String,
            var product_id: Int,
            var product_image: String,
            var product_name: String,
            var updated_at: String,
            var varients: List<Varient>
        ) {
            data class Varient(
                var city_id: Int,
                var created_at: String,
                var description: String,
                var id: Int,
                var is_active: String,
                var mrp: Int,
                var price: Int,
                var product_id: Int,
                var quantity: Int,
                var unit: String,
                var updated_at: String,
                var variant_order: Int,
                var varient_id: Int,
                var varient_image: String
            )
        }
    }
}