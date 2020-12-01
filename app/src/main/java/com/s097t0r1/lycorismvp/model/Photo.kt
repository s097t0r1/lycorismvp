package com.s097t0r1.lycorismvp.model

import com.s097t0r1.lycorismvp.model.source.local.DatabasePhoto

data class Photo(
    val id: String,
    val imageUrl: String,
    val description: String,
    var isFavorite: Boolean
)

fun Photo.toDatabaseModel(): DatabasePhoto =
    DatabasePhoto(
        id = id,
        imageUrl = imageUrl,
        description = description
    )