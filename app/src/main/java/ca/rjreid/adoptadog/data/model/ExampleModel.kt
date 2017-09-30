package ca.rjreid.adoptadog.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class ExampleModel(val id: String, @SerializedName("diff_name") val name: String) : Parcelable {
    //region Parcelable Implementation
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(name)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ExampleModel> = object : Parcelable.Creator<ExampleModel> {
            override fun createFromParcel(source: Parcel): ExampleModel = ExampleModel(source)
            override fun newArray(size: Int): Array<ExampleModel?> = arrayOfNulls(size)
        }
    }
    //endregion
}