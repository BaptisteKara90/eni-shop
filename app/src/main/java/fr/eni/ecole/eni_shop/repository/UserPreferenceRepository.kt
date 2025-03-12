package fr.eni.ecole.eni_shop.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferenceRepository(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("user_preference")

    val KEY_DARKTHEME_SELECTED = booleanPreferencesKey("dark_theme_selected")

    suspend fun setDarkThemeSelected(value: Boolean) {
        context.dataStore.edit { pref ->
            pref[KEY_DARKTHEME_SELECTED] = value
        }
    }

    suspend fun getDarkThemeSelected(): Flow<Boolean> {
        return context.dataStore.data.map { pref ->
            pref[KEY_DARKTHEME_SELECTED] ?: false
        }
    }
}