package com.notiprice.config.jwt

import com.notiprice.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

fun User.toCustomUserDetails(): CustomUserDetails {
    //println("username: $username password: $password")
    return CustomUserDetails(username, password, Collections.singletonList(SimpleGrantedAuthority("ROLE_USER")))
}

class CustomUserDetails(
    private val login: String,
    private val password: String,
    private val grantedAuthorities: MutableCollection<out GrantedAuthority>
) : UserDetails{

    override fun getAuthorities() = grantedAuthorities

    override fun getPassword() = password

    override fun getUsername() = login

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

}