package uz.triples.societycare.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.home_pager_adapter.view.*
import uz.triples.societycare.R
import uz.triples.societycare.model.Sponsors

class ViewPagerAdapter(private val context: Context, private val listSponsors: List<Sponsors>) :
    PagerAdapter() {
    override fun getCount(): Int {
        return 3
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view =
            LayoutInflater.from(context).inflate(R.layout.home_pager_adapter, container, false)
        view.logo.setImageResource(listSponsors[position].image)
        view.description.text = listSponsors[position].description
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

}