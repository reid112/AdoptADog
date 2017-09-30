package ca.rjreid.adoptadog.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Dog(val image: String,
               val name: String,
               val breed: String,
               val sex: String,
               val age: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(image)
        writeString(name)
        writeString(breed)
        writeString(sex)
        writeString(age)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Dog> = object : Parcelable.Creator<Dog> {
            override fun createFromParcel(source: Parcel): Dog = Dog(source)
            override fun newArray(size: Int): Array<Dog?> = arrayOfNulls(size)
        }
    }
}