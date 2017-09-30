package ca.rjreid.adoptadog.data.model

import android.os.Parcel
import android.os.Parcelable

data class Vaccination(val name: String, val recieved: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(recieved)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Vaccination> = object : Parcelable.Creator<Vaccination> {
            override fun createFromParcel(source: Parcel): Vaccination = Vaccination(source)
            override fun newArray(size: Int): Array<Vaccination?> = arrayOfNulls(size)
        }
    }
}
