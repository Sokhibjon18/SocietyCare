package uz.triples.societycare.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uz.triples.societycare.database.entities.PoorlySupplied
import uz.triples.societycare.database.entities.Work
import uz.triples.societycare.repository.SeekForJobRepository
import uz.triples.societycare.repository.SocietyHelpRepository

class SeekForJobViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SeekForJobRepository(application)

    fun update(){
        repository.updateDatabase()
    }

    fun getWorkList(): LiveData<List<Work>> {
        return repository.getPoorlySuppliedList()
    }

    fun getWorkListQ(): LiveData<List<Work>> {
        return repository.getPoorlySuppliedListQ()
    }
}