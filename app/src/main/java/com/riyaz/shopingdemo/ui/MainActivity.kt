package com.riyaz.shopingdemo.ui

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.riyaz.shopingdemo.R
import com.riyaz.shopingdemo.ui.adapter.ShoppingItemAdapter
import com.riyaz.shopingdemo.databinding.ActivityMainBinding
import com.riyaz.shopingdemo.model.ShoppingItemHeight


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var shoppingAdapter: ShoppingItemAdapter
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* here, I am making use of Data Binding for simplicity
        though, it is not very efficient to use Data Binding here since we don't have many object to refer to in our layout. */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR)

        //initializing viewModel
        viewModel = ViewModelProvider(this, MainActivityViewModelFactory()).get(MainViewModel::class.java)

        //Initializing adapter for our recyclerview
        shoppingAdapter = ShoppingItemAdapter()

        //filling the adapter with demo data
        shoppingAdapter.setItemList(viewModel.shoppingItemList)

        binding.recyclerView.adapter = shoppingAdapter
        layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = layoutManager
        
        
        //here, we are observing the itemHeight(height of the shopping item in the grid) property in defined in the viewModel
        //it changes whenever we select different option from the overflow menu
        /*NOTE: I added this feature(changing shopping-item-view height)
          because I really liked the item height to be equal to the screen width
          but according to the assignment the height should be half the screen width
          so i created an overflow menu which allow us to choose the height
         */
        viewModel.itemHeight.observe(this, Observer { selectedHeight ->
            shoppingAdapter.setItemHeight(selectedHeight)

            //again, the below function is only to represent layout with different items heights
            forceRedrawRecyclerView(binding)
        })

        Toast.makeText(this, "item names are randomly picked from internet. Please, ignore them.", Toast.LENGTH_SHORT).show()
    }

    private fun forceRedrawRecyclerView(binding: ActivityMainBinding?) {
        binding.apply {
            this?.recyclerView?.setAdapter(null);
            this?.recyclerView?.setLayoutManager(null);
            this?.recyclerView?.setAdapter(shoppingAdapter);
            this?.recyclerView?.setLayoutManager(layoutManager);
            shoppingAdapter.notifyDataSetChanged();
        }
    }

    //I have created an overflow menu with two options
    //just to show items with two different height
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = this.menuInflater
        inflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.width_half -> {
                viewModel.setItemHeight(ShoppingItemHeight.SCREEN_WIDTH_HALF)
                true
            }
            R.id.width_full -> {
                viewModel.setItemHeight(ShoppingItemHeight.SCREEN_WIDTH_FULL)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}