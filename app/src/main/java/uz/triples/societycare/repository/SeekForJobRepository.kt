package uz.triples.societycare.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.triples.societycare.database.AppDatabase
import uz.triples.societycare.database.entities.PoorlySupplied
import uz.triples.societycare.database.entities.Work

class SeekForJobRepository(private val application: Application) {

    private val firebaseDatabase = FirebaseDatabase.getInstance().reference
    private val database = AppDatabase.getInstance(application)
    private val TAG = "SocietyHelpRepository"

    fun updateDatabase() {
        firebaseDatabase.child("IshUrinlari").child("Qoraqalpogiston").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var list = listOf<Work>()
                    for (work in snapshot.children) {
                        list = list + Work(
                            work.key!!.toInt(),
                            work.child("G8").value.toString(),
                            work.child("G1").value.toString(),
                            work.child("G7").value.toString(),
                            work.child("G5").value.toString(),
                            work.child("G9").value.toString()
                        )
                    }
                    database!!.workDao().insertWork(list)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            }
        )

        firebaseDatabase.child("IshUrinlari").child("Buhoro").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var list = listOf<Work>()
                    for (work in snapshot.children) {
                        list = list + Work(
                            work.key!!.toInt() + 1000,
                            work.child("G4").value.toString(),
                            work.child("G7").value.toString(),
                            work.child("G10").value.toString(),
                            work.child("G12").value.toString(),
                            "916654355"
                        )
                    }
                    database!!.workDao().insertWork(list)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            }
        )
    }

    fun getPoorlySuppliedList(): LiveData<List<Work>> {
        return database!!.workDao().getWorks()
    }

    fun getPoorlySuppliedListQ(): LiveData<List<Work>> {
        return database!!.workDao().getWorksQ()
    }
}