package com.awesomepetprojects.movieaday.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.awesomepetprojects.movieaday.data.models.RemoteKey

@Dao
interface RemoteKeysDao {

    @Query("SELECT next_page FROM remote_keys ORDER BY id DESC LIMIT 1")
    suspend fun getNextPage(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(key: RemoteKey)

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAllKeys()
}