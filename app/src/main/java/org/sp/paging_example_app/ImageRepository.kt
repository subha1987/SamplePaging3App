package org.sp.paging_example_app

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData

class ImageRepository(
    val imageService: ImageService
) {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 10
    }

    /**
     * let's define page size, page size is the only required param, rest is optional
     */
    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

    //for live data users
    fun getImagePagingDataLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<Photo>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { ImagePagingSource(imageService) }
        ).liveData
    }


}