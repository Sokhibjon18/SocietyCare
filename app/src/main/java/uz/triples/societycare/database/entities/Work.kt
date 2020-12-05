package uz.triples.societycare.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Work(
    @PrimaryKey
    val id: Int,
    val address: String?,
    val job: String?,
    val requirement: String?,
    val salary: String?,
    val phone: String?
)