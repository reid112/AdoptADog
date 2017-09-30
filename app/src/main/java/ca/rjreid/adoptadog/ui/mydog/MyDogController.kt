package ca.rjreid.adoptadog.ui.mydog

import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.rjreid.adoptadog.R
import com.bluelinelabs.conductor.Controller

class MyDogController : Controller() {

    override fun onCreateView(@NonNull inflater: LayoutInflater, @NonNull container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_my_dog, container, false)
        return view
    }

}