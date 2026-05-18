package com.example.gandhasiri

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TreeDao {

    @Insert
    fun insertTree(tree: Tree)

    @Query("SELECT * FROM trees ORDER BY id DESC")
    fun getAllTrees(): List<Tree>

    @Query("SELECT COUNT(*) FROM trees")
    fun getTreeCount(): Int

    @Query("SELECT COUNT(*) FROM trees WHERE health = 'Mature'")
    fun getMatureCount(): Int
}