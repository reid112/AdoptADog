package ca.rjreid.adoptadog.ui.views

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import ca.rjreid.adoptadog.R
import ca.rjreid.adoptadog.data.model.Dog
import kotlinx.android.synthetic.main.component_general_card.view.*

class GeneralCard @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : CardView(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.component_general_card, this, true)
    }

    fun setData(dog: Dog) {
        val layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        val personalityTextView = TextView(context)
        personalityTextView.text = "Personality: ${dog.personality}"
        personalityTextView.layoutParams = layoutParams
        contentHolder.addView(personalityTextView)

        val kennelTextView = TextView(context)
        kennelTextView.text = "Kennel Trained: ${dog.kennelTrained}"
        kennelTextView.layoutParams = layoutParams
        contentHolder.addView(kennelTextView)

        val houseTextView = TextView(context)
        houseTextView.text = "House Trained: ${dog.houseTrained}"
        houseTextView.layoutParams = layoutParams
        contentHolder.addView(houseTextView)
    }
}