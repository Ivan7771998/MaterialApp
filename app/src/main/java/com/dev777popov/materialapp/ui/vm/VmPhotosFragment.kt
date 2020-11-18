package com.dev777popov.materialapp.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev777popov.materialapp.tools.DataStoreHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VmPhotosFragment: ViewModel() {

    private val imageList: MutableLiveData<MutableList<String>> = MutableLiveData()

    fun updateImageList(dataStoreHelper: DataStoreHelper) {
        viewModelScope.launch (Dispatchers.IO){
            imageList.postValue(dataStoreHelper.getPhotoList())
        }
    }

    fun deleteImage(dataStoreHelper: DataStoreHelper , image: String) {
        viewModelScope.launch (Dispatchers.IO){
            dataStoreHelper.deleteImageFile(image)
            updateImageList(dataStoreHelper)
        }
    }

    fun getImageLiveData(): LiveData<MutableList<String>> {
        return imageList
    }
}