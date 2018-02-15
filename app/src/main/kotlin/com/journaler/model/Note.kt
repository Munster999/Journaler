package com.journaler.model

import android.location.Location
import android.os.Parcel
import android.os.Parcelable

class Note(
        title: String,
        message: String,
        location: Location
    ) : Entry(
            title,
            message,
            location ), Parcelable {

    override var id = 0L

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Location::class.java.classLoader)
    ) {
        id = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(message)
        parcel.writeParcelable(location, 0)
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
      return 0
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }

}

/*
NOTE: we put the current geolocation as the information stored in our note along with 'title' and note 'message' content.
- We also overrode the ID
- Since the newly instantiated note is not yet stored into the database, its ID will be zero.
- After we store it, it will be updated to the ID value obtained from the database.



*/