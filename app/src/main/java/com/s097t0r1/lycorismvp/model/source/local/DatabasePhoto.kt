package com.s097t0r1.lycorismvp.model.source.local

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.s097t0r1.lycorismvp.model.Photo

@Entity(tableName = "photos")
data class DatabasePhoto (
    @PrimaryKey
    val id: String,
    val imageUrl: String,
    val description: String
)

fun DatabasePhoto.toDomainModel(): Photo {
    return Photo(
        id = id,
        imageUrl = imageUrl,
        description = description,
        isFavorite = true
    )
}

fun List<DatabasePhoto>.toDomainModel(): List<Photo> {
    return this.map {
        it.toDomainModel()
    }
}