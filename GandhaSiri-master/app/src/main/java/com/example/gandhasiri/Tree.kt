package com.example.gandhasiri

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trees")
data class Tree(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val treeId: String,
    val girth: Int,
    val age: Int,
    val gps: String,
    val notes: String,
    val health: String,
    val dateAdded: String,
    val photoPath: String = ""   // NEW — stores path to tree photo
)