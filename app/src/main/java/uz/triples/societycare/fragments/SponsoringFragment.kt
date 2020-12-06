package uz.triples.societycare.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import kotlinx.android.synthetic.main.fragment_sponsoring.*
import uz.triples.societycare.R
import uz.triples.societycare.Sponsoring
import uz.triples.societycare.adapters.SponsorRV
import uz.triples.societycare.viewModels.SponsoringViewModel

class SponsoringFragment : Fragment(R.layout.fragment_sponsoring) {

    lateinit var adapter: SponsorRV
    private val TAG = "SponsoringFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: SponsoringViewModel by viewModels()
        viewModel.update()

        adapter = SponsorRV(requireContext(), object : Sponsoring{
            override fun call(sponsoring: uz.triples.societycare.database.entities.Sponsoring) {
                val alertDialog = AlertDialog.Builder(requireContext())
                alertDialog.setTitle("Ma'lumot")
                alertDialog.setMessage("Click API ulanmagan.\nUshbu muassasa bilan bog'lanish uchun ${sponsoring.phone} raqamiga qo'ng'iroq qiling")
                alertDialog.setPositiveButton("OK") { _, _ ->

                }
                alertDialog.setNeutralButton("Qo'ng'iroq qilish") { _, _ ->
                    Permissions.check(
                        requireContext(),
                        Manifest.permission.CALL_PHONE,
                        null,
                        object : PermissionHandler() {
                            override fun onGranted() {
                                val callIntent = Intent(Intent.ACTION_CALL)
                                callIntent.data =
                                    Uri.parse("tel:${sponsoring.phone}")
                                startActivity(callIntent)
                            }
                        })
                }
                alertDialog.show()
            }
        })

        sponsoringRV.layoutManager = LinearLayoutManager(requireContext())
        sponsoringRV.adapter = adapter

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        shareBtn.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(
                Intent.EXTRA_TEXT,
                "https://play.google.com/store/apps/details?id=uz.triples.gdgdevfest"
            )
            startActivity(Intent.createChooser(sharingIntent, "Share"))
        }

        viewModel.getSponsoringList().observe(viewLifecycleOwner, {
            adapter.submitList(it)
            Log.d(TAG, "onViewCreated: ${it.size}")
        })
    }
}