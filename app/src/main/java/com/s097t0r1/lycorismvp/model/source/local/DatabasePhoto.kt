package com.s097t0r1.lycorismvp.model.source.local

import androidx.room.Entity

@Entity(tableName = "photos_table")
data class DatabasePhoto (
    val id: String,
    val imageUrl: String,
    val description: String
)