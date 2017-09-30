package ca.rjreid.adoptadog.ui.profile

import android.content.Intent
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
import ca.rjreid.adoptadog.data.DataManager
import ca.rjreid.adoptadog.ui.main.MainActivity
import ca.rjreid.adoptadog.util.image
import com.bluelinelabs.conductor.Controller
import com.facebook.login.widget.LoginButton
import io.smooch.ui.ConversationActivity
import de.hdodenhof.circleimageview.CircleImageView
import io.smooch.core.User
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import android.os.Bundle
import ca.rjreid.adoptadog.data.model.Person
import com.facebook.*
import android.app.ProgressDialog
import com.facebook.GraphResponse
import org.json.JSONObject
import com.facebook.GraphRequest
import timber.log.Timber


class ProfileController : Controller() {

    private val FOSTER_APPLICATION_URL = "http://ccrezqs.com/foster-application/"
    private val ADOPTION_APPLICATION_URL = "http://ccrezqs.com/adoption-application/"

    private lateinit var messageButton: Button
    private lateinit var fosterButton: Button
    private lateinit var adoptionButton: Button
    private lateinit var facebookLoginButton: LoginButton

    private lateinit var fullName : TextView
    private lateinit var dob : TextView
    private lateinit var memberSince : TextView

    private lateinit var profilePicture : CircleImageView

    private var dm: DataManager? = null
    private var callbackManager: CallbackManager? = null

    override fun onCreateView(@NonNull inflater: LayoutInflater, @NonNull container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_profile, container, false)

        messageButton = view.findViewById(R.id.messageButton)
        fosterButton = view.findViewById(R.id.fosterButton)
        adoptionButton = view.findViewById(R.id.adoptionButton)
        fullName = view.findViewById(R.id.fullName)
        dob = view.findViewById(R.id.dob)
        memberSince = view.findViewById(R.id.memberSince)
        profilePicture = view.findViewById(R.id.profilePicture)
        facebookLoginButton = view.findViewById(R.id.facebookLoginButton)

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)

        if (activity is MainActivity) {
            (activity as MainActivity).supportActionBar?.title = "Profile"
        }

        facebookLoginButton.setReadPermissions("public_profile", "email", "user_birthday")
        callbackManager = CallbackManager.Factory.create()

        facebookLoginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        val progressDialog = ProgressDialog(activity)
                        progressDialog.setMessage("Logging in...")
                        progressDialog.show()
                        val accessToken = loginResult.accessToken.token

                        val request = GraphRequest.newMeRequest(loginResult.accessToken) { `object`, response ->
                            val firstName = response.jsonObject.get("first_name") as String
                            val lastName = response.jsonObject.get("last_name") as String
                            val email = response.jsonObject.get("email") as String
                            val dob = response.jsonObject.get("birthday") as String
                            val gender = response.jsonObject.get("gender") as String

                            dm?.register(Person("$firstName $lastName", dob, email, "", false, accessToken))?.subscribe()

                            progressDialog.dismiss()
                        }

                        val parameters = Bundle()
                        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location")
                        request.parameters = parameters
                        request.executeAsync()
                    }

                    override fun onCancel() {
                        // App code
                    }

                    override fun onError(exception: FacebookException) {
                        // App code
                    }
                })

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

        User.getCurrentUser().setFirstName("Riley")
        User.getCurrentUser().setLastName("Reid")

        val customProperties = HashMap<String, Any>()
        customProperties.put("Fostering Dog", "Gordon")
        User.getCurrentUser().addProperties(customProperties)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    //region Setters
    fun setDataManager(dm: DataManager) {
        this.dm = dm
    }
    //endregion



}