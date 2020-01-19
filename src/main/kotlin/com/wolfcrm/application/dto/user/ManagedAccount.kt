package com.wolfcrm.application.dto.user

import com.fasterxml.jackson.annotation.JsonProperty

data class ManagedAccount(
        @JsonProperty
        var firstName: String? = null,
        @JsonProperty
        var lastName: String? = null,
        @JsonProperty
        var middleName: String? = null,
        @JsonProperty
        var phone: String? = null
)
