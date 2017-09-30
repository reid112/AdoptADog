package ca.rjreid.adoptadog.ui.list

import android.support.annotation.NonNull
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import ca.rjreid.adoptadog.R
import com.bluelinelabs.conductor.Controller


class ListController : Controller() {

    override fun onCreateView(@NonNull inflater: LayoutInflater, @NonNull container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_list, container, false)
        return view
    }

}
