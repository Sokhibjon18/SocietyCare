package uz.triples.societycare

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import uz.triples.societycare.adapters.ViewPagerAdapter
import uz.triples.societycare.model.Sponsors

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewPager?.adapter = ViewPagerAdapter(requireContext(),
            listOf(
                Sponsors(R.drawable.logo, resources.getString(R.string.aboutApp)),
                Sponsors(R.drawable.it_park, resources.getString(R.string.itPark)),
                Sponsors(R.drawable.uz, resources.getString(R.string.uzbekCoders))
            )
        )

        tab_layout?.setupWithViewPager(homeViewPager)
    }
}