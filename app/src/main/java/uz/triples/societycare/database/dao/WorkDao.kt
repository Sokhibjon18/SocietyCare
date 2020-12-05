package uz.triples.societycare.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.triples.societycare.database.entities.PoorlySupplied
import uz.triples.societycare.database.entities.Work

@Dao
interface WorkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWork(works: List<Work>)

    @Query("SELECT * FROM work where id>1000")
    fun getWorks(): LiveData<List<Work>>

    @Query("SELECT * FROM work where id<1000")
    fun getWorksQ(): LiveData<List<Work>>
}