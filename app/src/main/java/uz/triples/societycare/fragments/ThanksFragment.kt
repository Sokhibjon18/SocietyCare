package uz.triples.societycare.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_thanks.*
import uz.triples.societycare.R

class ThanksFragment : Fragment(R.layout.fragment_thanks) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thanks.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}