package ca.rjreid.adoptadog.data.model

import android.os.Parcel
import android.os.Parcelable

data class Medication(val name: String, val freq: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(freq)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Medication> = object : Parcelable.Creator<Medication> {
            override fun createFromParcel(source: Parcel): Medication = Medication(source)
            override fun newArray(size: Int): Array<Medication?> = arrayOfNulls(size)
        }
    }
}
