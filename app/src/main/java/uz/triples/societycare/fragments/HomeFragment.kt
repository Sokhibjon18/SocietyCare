package uz.triples.societycare.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*
import uz.triples.societycare.R
import uz.triples.societycare.adapters.ViewPagerAdapter
import uz.triples.societycare.model.Sponsors

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewPager?.adapter = ViewPagerAdapter(
            requireContext(),
            listOf(
                Sponsors(R.drawable.logo, resources.getString(R.string.aboutApp)),
                Sponsors(R.drawable.it_park, resources.getString(R.string.itPark)),
                Sponsors(R.drawable.uz, resources.getString(R.string.uzbekCoders))
            )
        )
        tab_layout?.setupWithViewPager(homeViewPager)

        societyHelpCard.setOnClickListener {
            openNextFragment(0)
        }
        workCard.setOnClickListener {
            openNextFragment(1)
        }
        sponsoringCard.setOnClickListener {
            openNextFragment(2)
        }
    }

    private fun openNextFragment(order: Int) {
        object : CountDownTimer(500, 500) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                when (order) {
                    0 -> findNavController().navigate(R.id.societyHelpFragment)
                    1 -> findNavController().navigate(R.id.seekForJobFragment)
                    2 -> findNavController().navigate(R.id.sponsoringFragment)
                }
            }

        }.start()
    }
}