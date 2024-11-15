package com.example.inventory.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inventory.dao.InventoryDao
import com.example.inventory.entity.Inventory

@Database(entities = [Inventory::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao
}
