package com.rumeysaozer.sqlite.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Not(val id: Int, val baslik: String, val not: String) :Parcelable{
}