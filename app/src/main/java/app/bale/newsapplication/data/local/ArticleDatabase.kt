package app.bale.newsapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.bale.newsapplication.data.model.Article

// UserDatabase represents database and contains the database holder and server the main access point for the underlying connection to your app's persisted, relational data.

@Database(
    entities = [Article::class],
    version = 1,                // <- Database version
    exportSchema = true
)
abstract class ArticleDatabase: RoomDatabase() { // <- Add 'abstract' keyword and extends RoomDatabase

    abstract fun articleDao(): ArticleDao

    companion object {

        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getDatabase(context: Context): ArticleDatabase{

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "article_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}