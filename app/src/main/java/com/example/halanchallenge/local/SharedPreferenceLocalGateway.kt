package com.example.halanchallenge.local

import com.example.halanchallenge.data.local.ILocalGateway
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.utils.base.Constants

class SharedPreferenceLocalGateway(private val sp: SharedPreferencesManager) : ILocalGateway {

    override fun setIsLoggedIn(isLoggedIn: Boolean) {
        sp.putBoolean(Constants.IS_LOGGED_IN, isLoggedIn)
    }

    override fun getIsLoggedIn(): Boolean {
        return sp.getBoolean(Constants.IS_LOGGED_IN, false)
    }

    override fun insertToken(token: String) {
        sp.putString(Constants.TOKEN_NAME, token)
    }

    override fun insertProfile(profile: LoginResponse.Profile) {
        profile.run {
            sp.putString(Constants.USER_ARABIC_NAME, this.name)
            sp.putString(Constants.USER_NAME, this.username)
            sp.putString(Constants.USER_EMAIL, this.email)
            sp.putString(Constants.USER_PHONE, this.phone)
            sp.putString(Constants.USER_IMAGE, this.image)
        }
    }

    override fun getToken(): String {
        return sp.getString(Constants.TOKEN_NAME, "") ?: ""
    }

    override fun getProfile(): LoginResponse.Profile {
        return LoginResponse.Profile(
            email = sp.getString(Constants.USER_EMAIL, ""),
            image = sp.getString(Constants.USER_IMAGE, ""),
            name =  sp.getString(Constants.USER_ARABIC_NAME, ""),
            phone = sp.getString(Constants.USER_ARABIC_NAME, ""),
            username = sp.getString(Constants.USER_NAME, "")
        )
    }

    override fun clearPreference() {
        sp.clearPreferences(true)
    }

}