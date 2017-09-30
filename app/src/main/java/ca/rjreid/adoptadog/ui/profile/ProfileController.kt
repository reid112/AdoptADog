package ca.rjreid.adoptadog.ui.profile

import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.rjreid.adoptadog.R
import com.bluelinelabs.conductor.Controller

class ProfileController : Controller() {

    override fun onCreateView(@NonNull inflater: LayoutInflater, @NonNull container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_profile, container, false)
        return view
    }

}