package com.s097t0r1.lycorismvp.model.source.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PhotoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPhoto(photo: DatabasePhoto)

    @Delete
    fun deletePhoto(photo: DatabasePhoto)

    @Query("SELECT * FROM photos WHERE id=:id")
    fun getPhoto(id: String): Observable<DatabasePhoto>

    @Query("SELECT * FROM photos")
    fun getPhotos(): Observable<List<DatabasePhoto>>
}