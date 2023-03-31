package com.notiprice.security

import com.notiprice.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

fun User.toCustomUserDetails(): CustomUserDetails {
    return CustomUserDetails(username, password, Collections.singletonList(SimpleGrantedAuthority("ROLE_USER")))
}

/**
 * Адаптер моего класса User для Spring Security.
 */
class CustomUserDetails(
    private val login: String,
    private val password: String,
    private val grantedAuthorities: MutableCollection<out GrantedAuthority>
) : UserDetails{
    /**
     * Получение ролей пользователя. В данной версии у пользователей только одна роль - ROLE_USER/
     */
    override fun getAuthorities() = grantedAuthorities

    /**
     * Получение пароля пользователя.
     */
    override fun getPassword() = password

    /**
     * Получение пользовательского имени.
     */
    override fun getUsername() = login

    /**
     * Не имеет значения. Для совместимости.
     */
    override fun isAccountNonExpired() = true
    /**
     * Не имеет значения. Для совместимости.
     */
    override fun isAccountNonLocked() = true
    /**
     * Не имеет значения. Для совместимости.
     */
    override fun isCredentialsNonExpired() = true
    /**
     * Не имеет значения. Для совместимости.
     */
    override fun isEnabled() = true

}