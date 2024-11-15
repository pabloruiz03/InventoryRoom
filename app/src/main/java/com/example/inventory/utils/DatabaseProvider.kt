// Import Room dependencies
import android.content.Context
import androidx.room.Room
import com.example.inventory.database.AppDatabase

// Singleton pattern for database setup
object DatabaseProvider {

    // Reference to the Room database
    private var INSTANCE: AppDatabase? = null

    // Function to get the database instance
    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            // Create the database instance if it doesn't exist
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "inventory_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
