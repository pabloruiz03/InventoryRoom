package com.example.inventory.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.inventory.entity.Inventory

@Dao
interface InventoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInventory(inventory: Inventory)

    @Query("SELECT * FROM Inventory")
    fun getAllInventory(): List<Inventory>

    @Query("SELECT SUM(quantity * cost_per_unit) FROM Inventory")
    suspend fun getTotalWorth(): Double
}
