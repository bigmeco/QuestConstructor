package com.bigmeco.questconstructor

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater



class StylesPagerAdapter(context: Context) : PagerAdapter() {

    private  var mContext: Context = context


    override fun instantiateItem(viewGroup: ViewGroup, position: Int): Any {
        val resources = ResourcesModel.values()[position]
        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(
                resources.layoutResourceId, viewGroup, false) as ViewGroup
        viewGroup.addView(layout)

        return layout
    }

    override fun destroyItem(viewGroup: ViewGroup, position: Int, view: Any) {
        viewGroup.removeView(view as View)
    }

    override fun isViewFromObject(view: View, p1: Any): Boolean {
        return view === p1

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.getString(R.string.nuul)
    }
    override fun getCount(): Int {
        return ResourcesModel.values().size

    }
}