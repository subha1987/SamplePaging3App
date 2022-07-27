package org.sp.paging_example_app

import androidx.paging.PagingSource
import org.sp.paging_example_app.ImageRepository.Companion.DEFAULT_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException


/**
 * It is a generic abstract class that is responsible for loading the paging data from the network.
 * Mainly this supply data during pagination
 */
class ImagePagingSource(val imageService: ImageService) :
    PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val photoResponse = imageService.getImages(page)
            LoadResult.Page(
                data = photoResponse.results, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (photoResponse.results.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}