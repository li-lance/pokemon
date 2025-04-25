package com.seraphim.shared.di

import com.seraphim.shared.database.AppDatabase

expect class Factory {
    fun createRoomDatabase(): AppDatabase
}