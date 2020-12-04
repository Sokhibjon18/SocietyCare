package uz.triples.societycare.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PoorlySupplied(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tel: String,
    val mother: String,
    val father: String,
    val children: String,
    val memberCount: Int,
    val sum: Float
)