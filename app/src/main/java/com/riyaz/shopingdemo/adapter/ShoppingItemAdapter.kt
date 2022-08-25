package com.riyaz.shopingdemo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riyaz.shopingdemo.R
import com.riyaz.shopingdemo.model.ShoppingItem
import com.riyaz.shopingdemo.model.ShoppingItemHeight

//If there was a real list with lots of items that frequently I probably would have Used ListAdapter class with DiffUtil utility class
class ShoppingItemAdapter() : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {
    private var itemList = listOf<ShoppingItem>()
    private var itemHeight = ShoppingItemHeight.WIDTH_HALF

    @JvmName("setItemList1")
    fun setItemList(list: List<ShoppingItem>) {
        itemList = list
        notifyDataSetChanged()
    }

    fun setItemHeight(shoppingItemHeight: ShoppingItemHeight) {
        itemHeight = shoppingItemHeight
    }

    class ShoppingItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image = view.findViewById<ImageView>(R.id.item_image)
        val title = view.findViewById<TextView>(R.id.item_title)
        val price = view.findViewById<TextView>(R.id.item_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.shopping_item_view, parent, false)
//        val height = parent.measuredHeight/2
//        view.minimumHeight = height
        setItemHeight(view, parent)
        return ShoppingItemViewHolder(view)
    }

    private fun setItemHeight(view: View, parent: ViewGroup) {
        val lp = view.getLayoutParams()

        val partOfScreenWidth = when (itemHeight) {
            ShoppingItemHeight.WIDTH_HALF -> {
                Log.d("Shopping Adapter", "2")
                2
            }
            ShoppingItemHeight.WIDTH_FULL -> {
                Log.d("Shopping Adapter", "1")
                1
            }
        }

        lp.height = parent.measuredWidth / partOfScreenWidth
        view.setLayoutParams(lp)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        holder.image.setImageResource(itemList[position].itemImage)
        holder.title.text = itemList[position].title
        holder.price.text = "₹ ${itemList[position].price}"
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}