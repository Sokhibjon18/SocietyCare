package uz.triples.societycare.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.triples.societycare.database.entities.PoorlySupplied
import uz.triples.societycare.database.entities.Sponsoring
import uz.triples.societycare.database.entities.Work

@Dao
interface SponsoringDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSponsoring(works: List<Sponsoring>)

    @Query("SELECT * FROM sponsoring")
    fun getSponsoring(): LiveData<List<Sponsoring>>
}