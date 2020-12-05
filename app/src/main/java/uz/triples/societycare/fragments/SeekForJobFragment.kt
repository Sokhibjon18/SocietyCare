package uz.triples.societycare.fragments

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import kotlinx.android.synthetic.main.fragment_seek_for_job.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.triples.societycare.CallForJob
import uz.triples.societycare.R
import uz.triples.societycare.adapters.WorkRV
import uz.triples.societycare.database.entities.Work
import uz.triples.societycare.viewModels.SeekForJobViewModel


class SeekForJobFragment : Fragment(R.layout.fragment_seek_for_job) {

    lateinit var adapter: WorkRV
    private val TAG = "SeekForJobFragment"
    private var listQoraqalpoq = listOf<Work>()
    private var listBukhara = listOf<Work>()
    private val regionName = listOf<String>("Bukhara", "Qoraqalpog'iston")
    private var boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: SeekForJobViewModel by viewModels()

        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, regionName)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = aa

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2){
                    0 -> adapter.submitList(listBukhara)
                    1 -> adapter.submitList(listQoraqalpoq)
                }
                object : CountDownTimer(500, 500) {
                    override fun onTick(p0: Long) {

                    }

                    override fun onFinish() {
                        workRV.scrollToPosition(0)
                    }
                }.start()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

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

        adapter = WorkRV(requireContext(), object : CallForJob {
            override fun call(string: String?) {
                Permissions.check(
                    requireContext(),
                    Manifest.permission.CALL_PHONE,
                    null,
                    object : PermissionHandler() {
                        override fun onGranted() {
                            val callIntent = Intent(Intent.ACTION_CALL)
                            callIntent.data =
                                Uri.parse("tel:$string")
                            startActivity(callIntent)
                        }
                    })
            }
        })
        workRV.layoutManager = GridLayoutManager(requireContext(), 2)
        workRV.adapter = adapter

        GlobalScope.launch {
            viewModel.update()

            launch(Dispatchers.Main) {
                viewModel.getWorkList().observe(viewLifecycleOwner, {
                    listBukhara = it
                    if (listBukhara.isNotEmpty() && !boolean){
                        adapter.submitList(it)
                        boolean = true
                    }
                })

                viewModel.getWorkListQ().observe(viewLifecycleOwner, {
                    listQoraqalpoq = it
                })
            }
        }
    }
}