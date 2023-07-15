package com.lrm.fakestore.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lrm.fakestore.model.Product
import com.lrm.fakestore.model.Products
import com.lrm.fakestore.network.ProductApi
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {

    private val _productsList = MutableLiveData<Products>()
    val productsList: LiveData<Products> get() = _productsList

    private val _dataList = MutableLiveData<List<Product>>()
    val dataList: LiveData<List<Product>> get() = _dataList

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> get() = _product

    init {
        getData()
    }

    private fun getData() {

        viewModelScope.launch {
            try {
                _productsList.value = ProductApi.retrofitService.getData()
                _dataList.value = productsList.value?.products
            } catch (e: Exception) {
                _dataList.value = listOf()
            }

            Log.i("MyLogMessages", "getData: ${dataList.value}")
            Log.i("MyLogMessages", "getData: ${productsList.value}")
            Log.i("MyLogMessages", "getData: ${product.value}")
        }
    }

    fun updateCurrentProduct(product: Product) {
        _product.value = product
    }
}