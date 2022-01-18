package com.umair.ecom.demo.data.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductItemResponse(
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("title")
    val title: String
) : Parcelable

@Parcelize
data class Rating(
    @SerializedName("count")
    val count: Int,
    @SerializedName("rate")
    val rate: Double
) : Parcelable