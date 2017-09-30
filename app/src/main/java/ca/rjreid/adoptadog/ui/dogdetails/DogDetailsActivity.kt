package ca.rjreid.adoptadog.ui.dogdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import ca.rjreid.adoptadog.R
import ca.rjreid.adoptadog.data.model.Dog
import ca.rjreid.adoptadog.ui.base.BaseActivity
import ca.rjreid.adoptadog.util.image
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_dog_details.*
import kotlinx.android.synthetic.main.component_dog_list_item.view.*
import javax.inject.Inject

class DogDetailsActivity : BaseActivity(), DogDetailsView {
    companion object {
        @JvmStatic
        fun createIntent(context: Context, dog: Dog) : Intent {
            val intent = Intent(context, DogDetailsActivity::class.java)
            intent.putExtra(DogDetailsPresenter.EXTRA_DOG, dog)
            return intent
        }
    }

    //region Variables
    @Inject lateinit var presenter: DogDetailsPresenter
    private var dog: Dog? = null
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        presenter.create()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        presenter.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        presenter.restoreInstanceState(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
    //endregion

    //region View Implementation
    override fun init() {
        dog = intent.extras.get(DogDetailsPresenter.EXTRA_DOG) as Dog
        dog?.let {
            dogImage.image(it.imageUrl)
            dogName.text = it.name
            dogAge.text = it.age
            dogBreed.text = it.breed
        }
    }
    //endregion

    //region BaseActivity Implementation
    override fun getLayout() = R.layout.activity_dog_details

    override fun getToolbar() : Toolbar? = toolbar

    override fun shouldShowBackButton() = true
    //endregion
}