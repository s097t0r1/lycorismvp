package com.s097t0r1.lycorismvp.model.source.remote

import io.reactivex.Observable
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val retrofitService: UnsplashAPI
){
    fun getPhotos(): Observable<List<RemotePhoto>> {
        return retrofitService.getPhotos()
            .map { response ->
                return@map JSONParser(response).parse()
            }
    }
}