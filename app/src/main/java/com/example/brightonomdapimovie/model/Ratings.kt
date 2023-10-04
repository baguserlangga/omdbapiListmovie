package com.example.brightonomdapimovie.model

import com.google.gson.annotations.SerializedName

class Ratings (
    @SerializedName("Source" ) var Source : String? = null,
    @SerializedName("Value"  ) var Value  : String? = null
        )
