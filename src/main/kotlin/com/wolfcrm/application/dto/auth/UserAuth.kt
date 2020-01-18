package com.wolfcrm.application.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class UserAuth(email: String, password: String) : Serializable {
    @JsonProperty
    var email: String? = email

    @JsonProperty
    var password: String? = password
}