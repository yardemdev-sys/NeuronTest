package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE id = 1")
    suspend fun getUser(): UserEntity?

    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Query("UPDATE user SET firstName = :firstName, lastName = :lastName WHERE id = 1")
    suspend fun updateUserName(
        firstName: String,
        lastName: String
    )

    @Query("DELETE FROM user WHERE id = 1")
    suspend fun deleteUser()
}
