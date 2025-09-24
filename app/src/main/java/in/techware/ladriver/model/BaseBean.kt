package `in`.techware.ladriver.model

import com.google.gson.annotations.SerializedName

open class BaseBean {
    @SerializedName("status")
    var status: String = ""

    @SerializedName("message")
    var message: String = ""

}