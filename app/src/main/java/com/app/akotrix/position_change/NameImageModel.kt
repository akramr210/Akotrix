package com.app.akotrix.position_change

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class NameImageModel(
    val name: String,
    val drawable: Int,
    var isSelected : Boolean? = null
) : Parcelable,Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.readInt(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(drawable)
        parcel.writeValue(isSelected)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NameImageModel> {
        override fun createFromParcel(parcel: Parcel): NameImageModel {
            return NameImageModel(parcel)
        }

        override fun newArray(size: Int): Array<NameImageModel?> {
            return arrayOfNulls(size)
        }
    }
}
