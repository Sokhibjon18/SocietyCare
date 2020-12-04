package uz.triples.societycare.`interface`

import uz.triples.societycare.database.entities.PoorlySupplied

interface ShareMoney {
    fun sendMoney(poorlySupplied: PoorlySupplied)
}