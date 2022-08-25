package com.riyaz.shopingdemo.model

import com.riyaz.shopingdemo.R

data class ShoppingItem(val itemId: Int, val itemImage: Int, val title: String, val price: Int)

fun getShoppingItemDemoList(): List<ShoppingItem> {
    return mutableListOf(
        ShoppingItem(1, R.drawable.bag_1, title = "Sacci Mucci Rainbow Sling Bag", 285),
        ShoppingItem(2, R.drawable.bag_2, title = "Envias Leatherette Side Sling Bags", 233),
        ShoppingItem(3, R.drawable.bag_5, title = "Gucci Chain Crossbody Bag", 799),
        ShoppingItem(4, R.drawable.bag_4, title = "Vismiintrend Stylish Sling Crossbody", 580),
        ShoppingItem(5, R.drawable.bag_3, title = "Vivinkaa Sling Bag", 215),
    )
}

enum class ShoppingItemHeight{
    WIDTH_FULL,
    WIDTH_HALF
}