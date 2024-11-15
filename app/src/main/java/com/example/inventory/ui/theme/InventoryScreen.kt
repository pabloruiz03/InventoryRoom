package com.example.inventory.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventory.entity.Inventory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun InventoryScreen() {
    val context = LocalContext.current
    val db = remember { DatabaseProvider.getDatabase(context) }
    val inventoryDao = db.inventoryDao()

    var totalWorth by remember { mutableStateOf(0.0) }
    var inventoryList by remember { mutableStateOf(listOf<Inventory>()) }
    val coroutineScope = rememberCoroutineScope()

    // Insert initial data and fetch total worth
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            // Insert sample data and fetch from the database on a background thread
            withContext(Dispatchers.IO) {
                inventoryDao.insertInventory(Inventory(name = "Apples", quantity = 50, costPerUnit = 0.75))
                inventoryDao.insertInventory(Inventory(name = "Oranges", quantity = 30, costPerUnit = 0.5))

                inventoryList = inventoryDao.getAllInventory()
                totalWorth = inventoryDao.getTotalWorth()
            }
            Log.d("InventoryApp", "Total monetary worth: $$totalWorth")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = "Inventory List",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(inventoryList) { item ->
                InventoryItemCard(item)
            }
        }

        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

        Text(
            text = "Total Worth: $$totalWorth",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp)
        )
    }
}

@Composable
fun InventoryItemCard(item: Inventory) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${item.quantity} units @ ${item.costPerUnit} each",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
