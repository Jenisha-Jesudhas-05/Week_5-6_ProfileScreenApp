package com.example.leanerapp.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class UserRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        val NAME_KEY = stringPreferencesKey("name")
        val ROLE_KEY = stringPreferencesKey("role")
        val BIO_KEY = stringPreferencesKey("bio")
    }

    val nameFlow: Flow<String> = context.dataStore.data
        .map { prefs -> prefs[NAME_KEY] ?: "Jenisha" }

    val roleFlow: Flow<String> = context.dataStore.data
        .map { prefs -> prefs[ROLE_KEY] ?: "Android Developer" }

    val bioFlow: Flow<String> = context.dataStore.data
        .map { prefs -> prefs[BIO_KEY] ?: "" }

    suspend fun saveProfile(name: String, role: String, bio: String) {
        context.dataStore.edit { prefs ->
            prefs[NAME_KEY] = name
            prefs[ROLE_KEY] = role
            prefs[BIO_KEY] = bio
        }
    }
}