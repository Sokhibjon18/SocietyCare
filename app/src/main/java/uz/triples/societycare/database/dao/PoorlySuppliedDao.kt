package uz.triples.societycare.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.triples.societycare.database.entities.PoorlySupplied

@Dao
interface PoorlySuppliedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFamily(poorlySupplied: PoorlySupplied)

    @Query("SELECT * FROM PoorlySupplied")
    fun getPoorlySupplied(): LiveData<List<PoorlySupplied>>
}