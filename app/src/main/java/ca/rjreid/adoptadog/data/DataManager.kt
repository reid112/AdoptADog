package ca.rjreid.adoptadog.data

import ca.rjreid.adoptadog.data.model.Dog
import ca.rjreid.adoptadog.data.model.Person
import io.reactivex.Observable

interface DataManager {
    fun fetchDogs(): Observable<List<Dog>>
    fun register(person: Person): Observable<String>
}