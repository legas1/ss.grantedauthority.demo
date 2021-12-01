package cz.legas.ss.grantedauthority.demo.auth

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class CustomAuthentication(
    private val authorities: Set<GrantedAuthority> = setOf(),
    private var authenticated: Boolean = false
) : Authentication {
    override fun getName(): String = "customAuth"

    override fun getAuthorities(): Set<GrantedAuthority> = authorities

    override fun getCredentials(): Any = "none"

    override fun getDetails(): Any = "none"

    override fun getPrincipal(): Any = "none"

    override fun isAuthenticated(): Boolean = authenticated

    override fun setAuthenticated(isAuthenticated: Boolean) { }
}
