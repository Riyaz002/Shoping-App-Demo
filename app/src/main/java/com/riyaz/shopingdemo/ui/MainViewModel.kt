package com.riyaz.shopingdemo.ui

import androidx.lifecycle.*
import com.riyaz.shopingdemo.model.ShoppingItem
import com.riyaz.shopingdemo.model.ShoppingItemHeight
import com.riyaz.shopingdemo.model.getShoppingItemDemoList


class MainViewModel(): ViewModel() {
    var shoppingItemList: List<ShoppingItem> = getShoppingItemDemoList()

    //initially the shopping item will have height equal to the screen width
    private val _itemHeight = MutableLiveData<ShoppingItemHeight>(ShoppingItemHeight.SCREEN_WIDTH_FULL)
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

//Factory for MainViewModel
class MainActivityViewModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel() as T
        }
        throw IllegalArgumentException("Class is not assignable")
    }

}