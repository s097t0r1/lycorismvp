package com.s097t0r1.lycorismvp.model.source.local

import com.s097t0r1.lycorismvp.model.Photo
import com.s097t0r1.lycorismvp.model.source.remote.RemoteDataSource
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: PhotoDAO
) {
    fun getPhotos(): Observable<List<DatabasePhoto>> {
        return dao.getPhotos()
    }
}