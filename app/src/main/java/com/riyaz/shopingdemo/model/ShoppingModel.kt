package com.riyaz.shopingdemo.model

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.riyaz.shopingdemo.R

data class ShoppingItem(val itemId: Int, val itemImage: Int, val title: String, val price: Int)

fun getShoppingItemDemoList(): List<ShoppingItem> {
    return mutableListOf(
        ShoppingItem(1, R.drawable.bag1, title = "Sacci Mucci Sling Bag", 1999),
        ShoppingItem(2, R.drawable.bag2, title = "Gucci Crossbody Bag", 2540),
        ShoppingItem(3, R.drawable.bag3, title = "Envias Leatherette Side Sling Bags", 799),
        ShoppingItem(4, R.drawable.bag4, title = "Vismiintrend Stylish Sling Crossbody", 580),
        ShoppingItem(5, R.drawable.bag5, title = "Vivinkaa Sling Bag", 1499),
    )
}

enum class ShoppingItemHeight{
    SCREEN_WIDTH_FULL,
    SCREEN_WIDTH_HALF
}