package ca.rjreid.adoptadog.ui.profile

import android.net.Uri
import android.support.annotation.NonNull
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ca.rjreid.adoptadog.R
import com.bluelinelabs.conductor.Controller
import io.smooch.ui.ConversationActivity

class ProfileController : Controller() {

    private val FOSTER_APPLICATION_URL = "http://ccrezqs.com/foster-application/"
    private val ADOPTION_APPLICATION_URL = "http://ccrezqs.com/adoption-application/"

    private lateinit var messageButton: Button
    private lateinit var fosterButton: Button
    private lateinit var adoptionButton: Button

    override fun onCreateView(@NonNull inflater: LayoutInflater, @NonNull container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_profile, container, false)

        messageButton = view.findViewById(R.id.messageButton)
        fosterButton = view.findViewById(R.id.fosterButton)
        adoptionButton = view.findViewById(R.id.adoptionButton)

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)

        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(activity, R.color.colorPrimary))
        val customTabsIntent = builder.build()

        messageButton.setOnClickListener { ConversationActivity.show(activity) }
        fosterButton.setOnClickListener { customTabsIntent.launchUrl(activity, Uri.parse(FOSTER_APPLICATION_URL)) }
        adoptionButton.setOnClickListener { customTabsIntent.launchUrl(activity, Uri.parse(ADOPTION_APPLICATION_URL)) }
    }
}