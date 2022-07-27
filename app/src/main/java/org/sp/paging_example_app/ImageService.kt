package org.sp.paging_example_app

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    //https://api.unsplash.com/search/photos?page=1&query=temples%20of%20india&client_id=fiJ6kE-NR1s4Cmyle9K5C1_U5JNFm4F5xYEWPxBu0CU
    @GET("/search/photos")
    suspend fun getImages(@Query("page") page: Int,
                               @Query("query") query: String = "temples%20of%20india",
                               @Query("client_id") client_id : String = "fiJ6kE-NR1s4Cmyle9K5C1_U5JNFm4F5xYEWPxBu0CU"): PhotoResponse
}


data class PhotoResponse(
    val results: List<Photo>,
    val total: Int,
    val total_pages: Int
)

data class Photo(
    val id: String,
    val urls: PhotoUrls,
)

data class PhotoUrls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val small_s3: String,
    val thumb: String
)

fun injectDoggoApiService(retrofit: Retrofit = getRetrofit()): ImageService {
    return retrofit.create(ImageService::class.java)
}


private fun getRetrofit(okHttpClient: OkHttpClient = getOkHttpClient()): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.unsplash.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()
}

private fun getOkHttpClient(
    okHttpLogger: HttpLoggingInterceptor = getHttpLogger(),
    okHttpNetworkInterceptor: Interceptor = getOkHttpNetworkInterceptor()
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(okHttpLogger)
        .addInterceptor(okHttpNetworkInterceptor)
        .build()
}

private fun getOkHttpNetworkInterceptor(): Interceptor {
    return Interceptor { chain ->
        val newRequest =
            chain.request().newBuilder().build()
        chain.proceed(newRequest)
    }
}

private fun getHttpLogger(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
