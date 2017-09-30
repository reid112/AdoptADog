package ca.rjreid.adoptadog.data

import ca.rjreid.adoptadog.data.model.Dog
import ca.rjreid.adoptadog.data.remote.CcRezQService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DataManagerWrapper @Inject constructor(private val service: CcRezQService) : DataManager {
    override fun fetchDogs(): Observable<List<Dog>> {
        return service.fetchDogs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}