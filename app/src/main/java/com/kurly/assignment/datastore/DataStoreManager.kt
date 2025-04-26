package com.kurly.assignment.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class DataStoreManager private constructor() {

    companion object {

        @Volatile
        private var instance: DataStoreManager? = null

        fun getInstance(): DataStoreManager {
            return instance ?: synchronized(this) {
                instance ?: DataStoreManager().also { instance = it }
            }
        }
    }

    suspend fun <T> put(context: Context, key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { prefs -> prefs[key] = value }
    }

    fun <T> get(context: Context, key: Preferences.Key<T>, default: T): Flow<T> {
        return context.dataStore.data.map { prefs -> prefs[key] ?: default }
    }

    suspend fun <T> remove(context: Context, key: Preferences.Key<T>) {
        context.dataStore.edit { prefs -> prefs.remove(key) }
    }
}
