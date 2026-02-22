package com.ibadetapp.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ibadetapp.data.model.BookmarkedAyah
import com.ibadetapp.data.model.DefaultZikirler
import com.ibadetapp.data.model.Zikir
import com.ibadetapp.data.model.ZikirSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Zikir::class, ZikirSession::class, BookmarkedAyah::class],
    version = 1,
    exportSchema = false
)
abstract class IbadetDatabase : RoomDatabase() {

    abstract fun zikirDao(): ZikirDao
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        @Volatile
        private var INSTANCE: IbadetDatabase? = null

        fun getDatabase(context: Context): IbadetDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IbadetDatabase::class.java,
                    "ibadet_database"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        INSTANCE?.let { database ->
                            CoroutineScope(Dispatchers.IO).launch {
                                // Varsayılan zikirler ekle
                                DefaultZikirler.list.forEach { zikir ->
                                    database.zikirDao().insert(zikir)
                                }
                            }
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
