package ca.rjreid.adoptadog.data.model

import android.os.Parcel
import android.os.Parcelable

data class HealthIssue(val name: String, val diagnosed: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(diagnosed)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<HealthIssue> = object : Parcelable.Creator<HealthIssue> {
            override fun createFromParcel(source: Parcel): HealthIssue = HealthIssue(source)
            override fun newArray(size: Int): Array<HealthIssue?> = arrayOfNulls(size)
        }
    }
}
