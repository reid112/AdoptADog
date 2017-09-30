package ca.rjreid.adoptadog.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Dog(val image: String,
               val name: String,
               val breed: String,
               val sex: String,
               val age: String,
               val kennelTrained: String,
               val houseTrained: String,
               val personality: String,
               val healthIssues: List<HealthIssue>?,
               val appointments: List<Appointment>?,
               val vaccinations: List<Vaccination>?,
               val medications: List<Medication>?) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.createTypedArrayList(HealthIssue.CREATOR),
            source.createTypedArrayList(Appointment.CREATOR),
            source.createTypedArrayList(Vaccination.CREATOR),
            source.createTypedArrayList(Medication.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(image)
        writeString(name)
        writeString(breed)
        writeString(sex)
        writeString(age)
        writeString(kennelTrained)
        writeString(houseTrained)
        writeString(personality)
        writeTypedList(healthIssues)
        writeTypedList(appointments)
        writeTypedList(vaccinations)
        writeTypedList(medications)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Dog> = object : Parcelable.Creator<Dog> {
            override fun createFromParcel(source: Parcel): Dog = Dog(source)
            override fun newArray(size: Int): Array<Dog?> = arrayOfNulls(size)
        }
    }
}