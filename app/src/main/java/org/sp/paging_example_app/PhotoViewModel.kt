package org.sp.paging_example_app

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import retrofit2.http.GET
import retrofit2.http.Query

class PhotoViewModel(private val imageRepository: ImageRepository) : ViewModel() {


    //live data use case
    fun fetchPhotoLiveData(): LiveData<PagingData<String>> {
        return imageRepository.getImagePagingDataLiveData()
            .map { it ->
                it.map { it.urls.regular }
            }.cachedIn(viewModelScope)
    }

}

class PhotoViewModelProviderFactory(private val imageRepository: ImageRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ImageRepository::class.java).newInstance(imageRepository)
    }

}