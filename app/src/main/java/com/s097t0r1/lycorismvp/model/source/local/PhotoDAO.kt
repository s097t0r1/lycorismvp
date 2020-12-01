package com.s097t0r1.lycorismvp.model.source.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PhotoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPhoto(photo: DatabasePhoto): Completable

    @Delete
    fun deletePhoto(photo: DatabasePhoto): Completable

    @Query("SELECT * FROM photos WHERE id=:id")
    fun getPhoto(id: String): Single<DatabasePhoto?>

    @Query("SELECT * FROM photos")
    fun getPhotos(): Single<List<DatabasePhoto>>
}