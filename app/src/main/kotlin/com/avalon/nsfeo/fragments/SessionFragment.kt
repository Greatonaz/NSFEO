package com.avalon.nsfeo.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerTabStrip
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarActivity
import android.support.v7.widget.Toolbar
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avalon.nsfeo.R

public class SessionFragment: Fragment() {

	private class SessionPageAdapter: FragmentPagerAdapter {

		private val context: Context
		private val login: Fragment
		private val create_account: Fragment

		public constructor(manager: FragmentManager, ctx: Context): super(manager) {

			this.context = ctx
			this.login = SessionLoginFragment()
			this.create_account = SessionCreateFragment()
		}

		override fun getItem(position: Int): Fragment {

			return when (position) {
				0 -> this.login
				1 -> this.create_account
				else -> throw IndexOutOfBoundsException()
			}
		}
		override fun getPageTitle(position: Int): CharSequence {

			return when (position) {
				0 -> this.context.getString(R.string.login).toUpperCase()
				1 -> this.context.getString(R.string.create_account).toUpperCase()
				else -> throw IndexOutOfBoundsException()
			}
		}
		override fun getCount(): Int = 2

	}

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, state: Bundle?): View {

		// Required variables for most operations
		val ctx = this.getActivity()
		val manager = this.getFragmentManager()

		// Inflate the view and set component behaviors
		with (inflater!!.inflate(R.layout.session, container, false)) {

			// Provide the adapter for the view pager
			val pager = this.findViewById(R.id.pager) as ViewPager
			pager.setAdapter(SessionPageAdapter(manager, ctx))

			// Set tab color for the view pager tabs: they should work automatically
			val tabs = this.findViewById(R.id.tabs) as PagerTabStrip
			tabs.setTabIndicatorColorResource(R.color.complement)
			tabs.setTextColor(ctx.getResources().getColor(android.R.color.white))
			tabs.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
			tabs.setDrawFullUnderline(true)

			// Set toolbar as action bar and configure
			val activity = (this@SessionFragment.getActivity() as ActionBarActivity)
			activity.setSupportActionBar((this.findViewById(R.id.toolbar) as Toolbar))
			activity.getSupportActionBar().setDisplayShowTitleEnabled(false)

			return this
		}
	}

}
