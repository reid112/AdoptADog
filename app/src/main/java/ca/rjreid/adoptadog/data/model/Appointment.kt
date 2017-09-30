package ca.rjreid.adoptadog.data.model

import android.os.Parcel
import android.os.Parcelable

data class Appointment(val name: String, val date: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(date)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Appointment> = object : Parcelable.Creator<Appointment> {
            override fun createFromParcel(source: Parcel): Appointment = Appointment(source)
            override fun newArray(size: Int): Array<Appointment?> = arrayOfNulls(size)
        }
    }
}
