package com.example.inventory.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.inventory.entity.Inventory

// Data Access Object (DAO) for accessing Inventory data in the database
@Dao
interface InventoryDao {

    // Inserts a new inventory item into the database
    // If an item with the same primary key already exists, it will be replaced
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInventory(inventory: Inventory)

    // Retrieves all inventory items from the database
    // Returns a list of Inventory objects
    @Query("SELECT * FROM Inventory")
    fun getAllInventory(): List<Inventory>

    // Calculates the total monetary worth of all inventory items
    // Multiplies the quantity of each item by its cost per unit and sums up the results
    @Query("SELECT SUM(quantity * cost_per_unit) FROM Inventory")
    suspend fun getTotalWorth(): Double
}
