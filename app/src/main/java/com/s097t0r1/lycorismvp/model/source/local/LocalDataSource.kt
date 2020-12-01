package com.s097t0r1.lycorismvp.model.source.local

import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: PhotoDAO
) {
    fun getPhotos(): Single<List<DatabasePhoto>> {
        return dao.getPhotos()
    }

    fun getPhoto(id: String): Single<DatabasePhoto?> {
        return dao.getPhoto(id)
    }

    fun insertPhoto(photo: DatabasePhoto): Completable {
        return dao.insertPhoto(photo)
    }

    fun deletePhoto(photo: DatabasePhoto): Completable {
        return dao.deletePhoto(photo)
    }
}