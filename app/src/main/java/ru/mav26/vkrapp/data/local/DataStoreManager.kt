package ru.mav26.vkrapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("dataStore")

class DataStoreManager(private val context: Context) {
    suspend fun saveTokens(tokens: TokenData) {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey("accessToken")] = tokens.accessToken
            pref[stringPreferencesKey("refreshToken")] = tokens.refreshToken
        }
    }

    fun getTokens() = context.dataStore.data.map { pref ->
        return@map TokenData(
            accessToken = pref[stringPreferencesKey("accessToken")] ?: "",
            refreshToken = pref[stringPreferencesKey("refreshToken")] ?: ""
        )
    }
}