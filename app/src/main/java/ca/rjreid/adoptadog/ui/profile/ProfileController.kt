package ca.rjreid.adoptadog.ui.profile

import android.net.Uri
import android.support.annotation.NonNull
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ca.rjreid.adoptadog.R
import ca.rjreid.adoptadog.ui.main.MainActivity
import ca.rjreid.adoptadog.util.image
import com.bluelinelabs.conductor.Controller
import io.smooch.ui.ConversationActivity
import de.hdodenhof.circleimageview.CircleImageView

class ProfileController : Controller() {

    private val FOSTER_APPLICATION_URL = "http://ccrezqs.com/foster-application/"
    private val ADOPTION_APPLICATION_URL = "http://ccrezqs.com/adoption-application/"

    private lateinit var messageButton: Button
    private lateinit var fosterButton: Button
    private lateinit var adoptionButton: Button

    private lateinit var fullName : TextView
    private lateinit var dob : TextView
    private lateinit var memberSince : TextView

    private lateinit var profilePicture : CircleImageView

    override fun onCreateView(@NonNull inflater: LayoutInflater, @NonNull container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_profile, container, false)

        messageButton = view.findViewById(R.id.messageButton)
        fosterButton = view.findViewById(R.id.fosterButton)
        adoptionButton = view.findViewById(R.id.adoptionButton)
        fullName = view.findViewById(R.id.fullName)
        dob = view.findViewById(R.id.dob)
        memberSince = view.findViewById(R.id.memberSince)
        profilePicture = view.findViewById(R.id.profilePicture)

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)

        if (activity is MainActivity) {
            (activity as MainActivity).supportActionBar?.title = "Profile"
        }

        fullName.text = "Riley Reid"
        dob.text = "March 20, 1991"
        memberSince.text = "Fostering since Sept 30, 2017"
        profilePicture.image("https://scontent.fyqr1-1.fna.fbcdn.net/v/t31.0-8/20776458_10159391956595722_2459934921221131332_o.jpg?oh=eb5f2311db7311f33fffa147c82ab4eb&oe=5A43AC62")


        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDarkish))
        val customTabsIntent = builder.build()

        messageButton.setOnClickListener { ConversationActivity.show(activity) }
        fosterButton.setOnClickListener { customTabsIntent.launchUrl(activity, Uri.parse(FOSTER_APPLICATION_URL)) }
        adoptionButton.setOnClickListener { customTabsIntent.launchUrl(activity, Uri.parse(ADOPTION_APPLICATION_URL)) }
    }
}