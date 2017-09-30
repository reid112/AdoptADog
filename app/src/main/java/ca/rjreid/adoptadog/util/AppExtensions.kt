package ca.rjreid.adoptadog.util

import android.view.View
import android.widget.ImageView
import ca.rjreid.adoptadog.R
import com.squareup.picasso.Picasso

fun ImageView.image(url: String) =
        Picasso.with(context)
                .load(url)
//                .placeholder(R.drawable.placeholder)
//                .error(R.drawable.error_placeholder)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(this)

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}