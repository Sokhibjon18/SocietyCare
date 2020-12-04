package uz.triples.societycare.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.triples.societycare.database.dao.PoorlySuppliedDao
import uz.triples.societycare.database.entities.PoorlySupplied

@Database(entities = [PoorlySupplied::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun poorlySuppliedDao(): PoorlySuppliedDao

    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, "SocietyCare.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }
            return instance
        }
    }
}