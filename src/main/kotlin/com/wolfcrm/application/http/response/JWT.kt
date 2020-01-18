package com.wolfcrm.application.http.response

import org.springframework.security.core.GrantedAuthority

class JWT (var accessToken: String?, var username: String?, val authorities: Collection<GrantedAuthority>) {
    var type = "Bearer"
}