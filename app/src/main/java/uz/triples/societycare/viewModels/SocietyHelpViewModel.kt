package uz.triples.societycare.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uz.triples.societycare.database.entities.PoorlySupplied
import uz.triples.societycare.repository.SocietyHelpRepository

class SocietyHelpViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SocietyHelpRepository(application)

    fun update(){
        repository.updateDatabase()
    }

    fun getPoorlySuppliedList(): LiveData<List<PoorlySupplied>> {
        return repository.getPoorlySuppliedList()
    }

    fun sendMoney(poorlySupplied: PoorlySupplied){
        repository.sendMoneyToUser(poorlySupplied)
    }
}