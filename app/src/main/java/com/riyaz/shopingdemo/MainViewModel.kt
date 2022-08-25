package com.riyaz.shopingdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riyaz.shopingdemo.model.ShoppingItem
import com.riyaz.shopingdemo.model.ShoppingItemHeight
import com.riyaz.shopingdemo.model.getShoppingItemDemoList


class MainViewModel: ViewModel() {
    var shoppingItemList: List<ShoppingItem> = getShoppingItemDemoList()

    //initially the shopping item will have height equal to the screen width
    private val _itemHeight = MutableLiveData<ShoppingItemHeight>(ShoppingItemHeight.WIDTH_FULL)
    val itemHeight: LiveData<ShoppingItemHeight> get() = _itemHeight

    fun setItemHeight(height: ShoppingItemHeight){

        /*
        since we will be drawing the recycler view again which is an expensive operation
        with the below if we are checking if we really need to draw it
        */
        if(_itemHeight.value != height){
            _itemHeight.value = height
        }
    }
}

class MainActivityViewModelFactory: ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel() as T
        }
        throw IllegalArgumentException("Class is not assignable")
    }

}