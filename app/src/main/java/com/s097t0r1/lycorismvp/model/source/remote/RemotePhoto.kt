package com.s097t0r1.lycorismvp.model.source.remote

import com.s097t0r1.lycorismvp.model.Photo

data class RemotePhoto(
    val id: String,
    val imageUrl: String,
    val description: String
)

fun RemotePhoto.toDomainModel(): Photo {
    return Photo(
        id = id,
        imageUrl = imageUrl,
        description = description,
        isFavorite = false
    )
}

fun List<RemotePhoto>.toDomainModel(): List<Photo> {
    return this.map {
        it.toDomainModel()
    }
}
