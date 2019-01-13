package network.responce

import com.google.gson.annotations.SerializedName

data class SearchByTitleResponse(
    @SerializedName("results")
    val entries: List<SearchByTitleEntry>
)