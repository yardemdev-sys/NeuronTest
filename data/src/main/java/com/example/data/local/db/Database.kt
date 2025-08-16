package com.example.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.UserDao
import com.example.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class NeuronTestDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}
