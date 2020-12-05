package uz.triples.societycare

import uz.triples.societycare.database.entities.PoorlySupplied
import uz.triples.societycare.database.entities.Sponsoring

interface ShareMoney {
    fun sendMoney(poorlySupplied: PoorlySupplied)
}

interface CallForJob {
    fun call(string: String?)
}

interface Sponsoring {
    fun call(sponsoring: Sponsoring)
}

