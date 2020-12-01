package com.s097t0r1.lycorismvp.model.source

import com.s097t0r1.lycorismvp.model.Photo
import com.s097t0r1.lycorismvp.model.source.local.LocalDataSource
import com.s097t0r1.lycorismvp.model.source.local.toDomainModel
import com.s097t0r1.lycorismvp.model.source.remote.RemoteDataSource
import com.s097t0r1.lycorismvp.model.source.remote.toDomainModel
import com.s097t0r1.lycorismvp.model.toDatabaseModel
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
){
    fun getPhotos(forceUpdate: Boolean): Single<List<Photo>> {
        if(forceUpdate) {
            return remoteDataSource.getPhotos()
                .map { it.toDomainModel() }
        }

        return localDataSource.getPhotos()
            .map { it.toDomainModel() }
    }

    fun getPhoto(id: String): Single<Photo> {
        return localDataSource.getPhoto(id)
            .map { it.toDomainModel() }
            .onErrorResumeNext { remoteDataSource.getPhoto(id).map { it.toDomainModel() } }
    }

    fun insertPhoto(photo: Photo): Completable {
        return localDataSource.insertPhoto(photo.toDatabaseModel())
    }

    fun deletePhoto(photo: Photo): Completable {
        return localDataSource.deletePhoto(photo.toDatabaseModel())
    }
}