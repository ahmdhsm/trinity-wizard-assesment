package com.ahmdhsm.trinitywizardcontact.models

import com.google.gson.annotations.SerializedName

data class ContactModel(
    @SerializedName("id"        ) var id        : String,
    @SerializedName("firstName" ) var firstName : String,
    @SerializedName("lastName"  ) var lastName  : String,
    @SerializedName("email"     ) var email     : String? = null,
    @SerializedName("dob"       ) var dob       : String? = null,
    @SerializedName("phone"     ) var phone     : String? = null
)