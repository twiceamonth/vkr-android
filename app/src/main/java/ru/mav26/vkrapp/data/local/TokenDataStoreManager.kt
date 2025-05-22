package ru.mav26.vkrapp.data.local

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val Context.tokenDataStore: DataStore<Preferences> by preferencesDataStore("tokens")

class TokenDataStoreManager(application: Application) : AndroidViewModel(application) {
    private val dataStore = application.tokenDataStore

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }

    val accessTokenFlow:  Flow<String?> = dataStore.data.map { it[ACCESS_TOKEN]  }
    val refreshTokenFlow: Flow<String?> = dataStore.data.map { it[REFRESH_TOKEN] }

    fun saveTokens(accessToken: String, refreshToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.edit { prefs ->
                prefs[ACCESS_TOKEN]  = accessToken
                prefs[REFRESH_TOKEN] = refreshToken
            }
        }
    }

    fun getAccessToken(onResult: (String?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val token: String? = accessTokenFlow.first()
            onResult(token)
        }
    }

    fun getRefreshToken(onResult: (String?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val token: String? = refreshTokenFlow.first()
            onResult(token)
        }
    }

    fun clearTokens() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.edit { prefs ->
                prefs.remove(ACCESS_TOKEN)
                prefs.remove(REFRESH_TOKEN)
            }
        }
    }
}