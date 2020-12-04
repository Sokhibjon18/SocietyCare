package uz.triples.societycare.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.triples.societycare.database.AppDatabase
import uz.triples.societycare.database.entities.PoorlySupplied

class SocietyHelpRepository(private val application: Application) {

    private val firebaseDatabase = FirebaseDatabase.getInstance().reference
    private val database = AppDatabase.getInstance(application)
    private val TAG = "SocietyHelpRepository"

    fun updateDatabase() {
        firebaseDatabase.child("Gijduvon").child("Biyosun").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (family in snapshot.children) {
                        val poorlySupplied = PoorlySupplied(
                            family.key!!.toInt(),
                            family.child("name").value.toString(),
                            family.child("tel").value.toString(),
                            family.child("ona").value.toString(),
                            family.child("ota").value.toString(),
                            family.child("farzandlar").value.toString(),
                            family.child("membersCount").value.toString().toInt(),
                            family.child("summa").value.toString().toFloat()
                        )
                        database!!.poorlySuppliedDao().insertFamily(poorlySupplied)
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            }
        )
    }

    fun getPoorlySuppliedList(): LiveData<List<PoorlySupplied>> {
        return database!!.poorlySuppliedDao().getPoorlySupplied()
    }

    fun sendMoneyToUser(poorlySupplied: PoorlySupplied) {
        val pre = poorlySupplied.sum
        firebaseDatabase.child("Gijduvon").child("Biyosun")
            .child(poorlySupplied.id.toString())
            .child("summa").setValue(pre + 750000)
    }
}