package uz.triples.societycare.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.triples.societycare.database.AppDatabase
import uz.triples.societycare.database.entities.PoorlySupplied
import uz.triples.societycare.database.entities.Sponsoring
import uz.triples.societycare.database.entities.Work

class SponsoringRepository(private val application: Application) {

    private val firebaseDatabase = FirebaseDatabase.getInstance().reference
    private val database = AppDatabase.getInstance(application)
    private val TAG = "SocietyHelpRepository"

    fun updateDatabase() {
        firebaseDatabase.child("Muassasalar").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var list = listOf<Sponsoring>()
                    for (organisation in snapshot.children) {
                        list = list + Sponsoring(
                            organisation.key!!.toInt(),
                            organisation.child("address").value.toString(),
                            organisation.child("name").value.toString(),
                            organisation.child("director").value.toString(),
                            organisation.child("phone").value.toString()
                        )
                    }
                    database!!.sponsoringDao().insertSponsoring(list)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            }
        )
    }

    fun getSponsoringList(): LiveData<List<Sponsoring>> {
        return database!!.sponsoringDao().getSponsoring()
    }

}