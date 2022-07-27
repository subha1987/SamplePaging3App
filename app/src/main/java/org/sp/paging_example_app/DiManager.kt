package org.sp.paging_example_app

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

private fun getImageServiceClass() : ImageService = injectDoggoApiService()
private fun getImageRepositoryClass() = ImageRepository(getImageServiceClass())
private fun getPhotoViewModelProviderFactory() = PhotoViewModelProviderFactory(getImageRepositoryClass())
private fun getPhotoViewModelProvider(owner : ViewModelStoreOwner) = ViewModelProvider(owner,getPhotoViewModelProviderFactory())
fun getPhotoViewModel(owner : ViewModelStoreOwner) = getPhotoViewModelProvider(owner).get(PhotoViewModel::class.java)