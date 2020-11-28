package com.s097t0r1.lycorismvp.model.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class DatabasePhoto (
    @PrimaryKey
    val id: String,
    val imageUrl: String,
    val description: String
)