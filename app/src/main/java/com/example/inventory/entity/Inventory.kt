package com.example.inventory.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

// Entity class representing an Inventory item in the database
// Each instance of this class represents a row in the Inventory table
@Entity(tableName = "Inventory")
data class Inventory(
    // Primary key for the Inventory table; auto-generates unique IDs
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    // Column for the name of the inventory item
    @ColumnInfo(name = "name") val name: String,

    // Column for the quantity of the inventory item
    @ColumnInfo(name = "quantity") val quantity: Int,

    // Column for the cost per unit of the inventory item
    @ColumnInfo(name = "cost_per_unit") val costPerUnit: Double
)
