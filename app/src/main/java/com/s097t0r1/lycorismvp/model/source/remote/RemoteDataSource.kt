package com.s097t0r1.lycorismvp.model.source.remote

import io.reactivex.Single
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val retrofitService: UnsplashAPI
){
    fun getPhotos(): Single<List<RemotePhoto>> {
        return retrofitService.getPhotos()
            .map { response -> return@map JSONParser.parse(JSONArray(response)) }
    }

    fun getPhoto(id: String): Single<RemotePhoto> {
        return retrofitService.getPhoto(id)
            .map { response -> return@map JSONParser.parse(JSONObject(response)) }
    }
}