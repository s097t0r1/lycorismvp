package com.s097t0r1.lycorismvp.model.source

import com.s097t0r1.lycorismvp.model.Photo
import com.s097t0r1.lycorismvp.model.source.local.LocalDataSource
import com.s097t0r1.lycorismvp.model.source.local.toDomainModel
import com.s097t0r1.lycorismvp.model.source.remote.RemoteDataSource
import com.s097t0r1.lycorismvp.model.source.remote.toDomainModel
import io.reactivex.Observable
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
){
    fun getPhotos(forceUpdate: Boolean): Observable<List<Photo>> {
        if(forceUpdate) {
            return remoteDataSource.getPhotos()
                .map { it.toDomainModel() }
        }

        return localDataSource.getPhotos()
            .map { it.toDomainModel() }
    }
}