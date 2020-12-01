package com.s097t0r1.lycorismvp.model.source.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

const val BASE_URL = "https://api.unsplash.com/"
const val ACCESS_KEY = "LRz23zhofhEVGQQmZzILVnBHb8ZpTGhYlQWkdRNY5OA"
const val AUTH_HEADER = "Authorization: Client-ID $ACCESS_KEY"

interface UnsplashAPI {

    @Headers(AUTH_HEADER)
    @GET("photos")
    fun getPhotos(): Single<String>

    @Headers(AUTH_HEADER)
    @GET("photos/{id}")
    fun getPhoto(@Path("id")id: String): Single<String>
}