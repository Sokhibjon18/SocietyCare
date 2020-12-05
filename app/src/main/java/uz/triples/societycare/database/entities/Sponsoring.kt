package uz.triples.societycare.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Sponsoring(
    @PrimaryKey
    val id: Int,
    val address: String?,
    val name: String?,
    val director: String?,
    val phone: String?
)