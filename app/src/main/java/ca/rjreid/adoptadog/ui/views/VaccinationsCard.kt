package ca.rjreid.adoptadog.ui.views

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import ca.rjreid.adoptadog.R
import ca.rjreid.adoptadog.data.model.Vaccination
import kotlinx.android.synthetic.main.component_health_issues_card.view.*

class VaccinationsCard @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : CardView(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.component_vaccinations_card, this, true)
    }

    fun setData(vaccinations: List<Vaccination>) {
        val layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        for (vaccination in vaccinations) {
            val view = TextView(context)
            view.text = "${vaccination.name} - Received ${vaccination.recieved}"
            view.layoutParams = layoutParams
            contentHolder.addView(view)
        }
    }
}