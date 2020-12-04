package uz.triples.societycare.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import kotlinx.android.synthetic.main.fragment_society_help.*
import uz.triples.societycare.R
import uz.triples.societycare.`interface`.ShareMoney
import uz.triples.societycare.adapters.SocietyRV
import uz.triples.societycare.database.entities.PoorlySupplied
import uz.triples.societycare.viewModels.SocietyHelpViewModel

class SocietyHelpFragment : Fragment(R.layout.fragment_society_help) {

    private val TAG = "SocietyHelpFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: SocietyHelpViewModel by viewModels()
        viewModel.update()

        val adapter = SocietyRV(requireContext(), object : ShareMoney {
            override fun sendMoney(poorlySupplied: PoorlySupplied) {
                if (poorlySupplied.sum >= 3000000f) {
                    Snackbar.make(
                        constraint,
                        "Sorry, for this family have enough money for this month",
                        5000
                    ).show()
                    return
                }
                lateinit var dialog: AlertDialog
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setTitle(poorlySupplied.name + "ga junatish")
                dialogBuilder.setMessage("Rostan ham mablag' utkazmoqchimisiz?\nAgarda test uchun mablag' utkazmoqchi bo'lsangiz Vertual tugmasini bosing. O'tkazish summasi 750 ming so'm")
                dialogBuilder.setPositiveButton("Vertual") { _, _ ->
                    viewModel.sendMoney(poorlySupplied)
                    object : CountDownTimer(1000, 1000) {
                        override fun onTick(p0: Long) {

                        }

                        override fun onFinish() {
                            findNavController().navigate(R.id.thanksFragment)
                        }

                    }.start()
                }
                dialogBuilder.setNeutralButton("O'tkazish") { _, _ ->
                    Permissions.check(
                        requireContext(),
                        Manifest.permission.CALL_PHONE,
                        null,
                        object : PermissionHandler() {
                            override fun onGranted() {
                                val callIntent = Intent(Intent.ACTION_CALL)
                                callIntent.data =
                                    Uri.parse("tel:*880*3*${poorlySupplied.tel}*750000%23")
                                startActivity(callIntent)
                            }
                        })
                }
                dialog = dialogBuilder.create()
                dialog.show()
            }
        })
        societyHelpRV.layoutManager = LinearLayoutManager(requireContext())
        societyHelpRV.adapter = adapter

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.getPoorlySuppliedList().observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

}