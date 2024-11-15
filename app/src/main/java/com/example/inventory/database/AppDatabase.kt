package com.example.inventory.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inventory.dao.InventoryDao
import com.example.inventory.entity.Inventory

// Room database class for the Inventory application
// This database holds the Inventory table and provides access to InventoryDao
@Database(entities = [Inventory::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // Abstract method to provide access to InventoryDao
    // This method will be implemented by Room at runtime
    abstract fun inventoryDao(): InventoryDao
}
