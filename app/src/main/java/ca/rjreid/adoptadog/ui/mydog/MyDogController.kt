package ca.rjreid.adoptadog.ui.mydog

import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.rjreid.adoptadog.R
import ca.rjreid.adoptadog.util.image
import com.bluelinelabs.conductor.Controller
import de.hdodenhof.circleimageview.CircleImageView

class MyDogController : Controller() {

    private lateinit var dogImage: CircleImageView

    override fun onCreateView(@NonNull inflater: LayoutInflater, @NonNull container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_my_dog, container, false)
        dogImage = view.findViewById(R.id.dogImage)
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        dogImage.image("http://www.nationalgeographic.com/content/dam/animals/thumbs/rights-exempt/mammals/d/domestic-dog_thumb.jpg")
    }
}