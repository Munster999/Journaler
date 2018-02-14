package com.journaler.model

import android.location.Location
import com.journaler.database.DbModel


abstract class Entry(
        var title: String,
        var message: String,
        var location: Location
) : DbModel()

/*
NOTE: we put the current geolocation as the information stored in our note along with 'title' and note 'message' content.



*/