package ca.rjreid.adoptadog.data

import ca.rjreid.adoptadog.data.model.Dog
import io.reactivex.Observable

interface DataManager {
    fun fetchDogs(): Observable<List<Dog>>
}