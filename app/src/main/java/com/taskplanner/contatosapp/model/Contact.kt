package com.taskplanner.contatosapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//Classe de modelo
@Parcelize
data class Contact(
    val name: String,
    val phone: String,
    val photo: String
) : Parcelable
