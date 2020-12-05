package uz.triples.societycare.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.triples.societycare.database.dao.PoorlySuppliedDao
import uz.triples.societycare.database.dao.SponsoringDao
import uz.triples.societycare.database.dao.WorkDao
import uz.triples.societycare.database.entities.PoorlySupplied
import uz.triples.societycare.database.entities.Sponsoring
import uz.triples.societycare.database.entities.Work

@Database(entities = [PoorlySupplied::class, Work::class, Sponsoring::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun poorlySuppliedDao(): PoorlySuppliedDao
    abstract fun workDao(): WorkDao
    abstract fun sponsoringDao(): SponsoringDao

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