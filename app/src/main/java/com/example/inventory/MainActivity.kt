package com.example.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.inventory.ui.InventoryScreen
import com.example.inventory.ui.theme.InventoryAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Test database initialization
        val db = DatabaseProvider.getDatabase(this)
        CoroutineScope(Dispatchers.IO).launch {
            db.inventoryDao().insertInventory(
                com.example.inventory.entity.Inventory(name = "Test Item", quantity = 5, costPerUnit = 10.0)
            )
        }

        setContent {
            InventoryAppTheme {
                Surface(color = MaterialTheme.colorScheme.primary) {
                    InventoryScreen()
                }
            }
        }
    }
}
