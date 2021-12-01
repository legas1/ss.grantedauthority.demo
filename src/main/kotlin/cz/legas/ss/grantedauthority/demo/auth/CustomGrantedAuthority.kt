package cz.legas.ss.grantedauthority.demo.auth

import org.springframework.security.core.GrantedAuthority

enum class CustomGrantedAuthority : GrantedAuthority {
    USER_AUTHORITY;

    override fun getAuthority(): String = this.toString()
}
