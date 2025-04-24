package ru.mav26.vkrapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.tokenDataStore: DataStore<Preferences> by preferencesDataStore("tokens")

class TokenDataStoreManager(private val context: Context) {
    private val dataStore = context.tokenDataStore

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }

    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }

    suspend fun getAccessToken(): String? {
        return dataStore.data
            .map { preferences ->
                preferences[ACCESS_TOKEN]
            }
            .first()
    }

    suspend fun getRefreshToken(): String? {
        return dataStore.data
            .map { preferences ->
                preferences[REFRESH_TOKEN]
            }
            .first()
    }

    suspend fun clearTokens() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN)
            preferences.remove(REFRESH_TOKEN)
        }
    }
}