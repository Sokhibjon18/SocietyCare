package uz.triples.societycare.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uz.triples.societycare.database.entities.PoorlySupplied
import uz.triples.societycare.database.entities.Sponsoring
import uz.triples.societycare.repository.SocietyHelpRepository
import uz.triples.societycare.repository.SponsoringRepository

class SponsoringViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SponsoringRepository(application)

    fun update(){
        repository.updateDatabase()
    }

    fun getSponsoringList(): LiveData<List<Sponsoring>> {
        return repository.getSponsoringList()
    }
}