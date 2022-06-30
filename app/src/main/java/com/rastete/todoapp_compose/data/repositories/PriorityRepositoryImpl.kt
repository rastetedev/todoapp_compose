package com.rastete.todoapp_compose.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rastete.todoapp_compose.data.repositories.PriorityRepositoryImpl.Companion.PREFERENCE_NAME
import com.rastete.todoapp_compose.domain.Priority
import com.rastete.todoapp_compose.domain.repository.PriorityRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

@ViewModelScoped
class PriorityRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) :
    PriorityRepository {

    private object PreferenceKeys {
        val sortStateKey = stringPreferencesKey(PREFERENCE_SORT_STATE_KEY)
    }

    private val dataStore = context.dataStore

    companion object {
        const val PREFERENCE_NAME = "todo_preferences"
        const val PREFERENCE_SORT_STATE_KEY = "sort_state"
    }

    override suspend fun savePriorityState(priority: Priority) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.sortStateKey] = priority.name
        }
    }

    override fun getPriorityState(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[PreferenceKeys.sortStateKey] ?: Priority.NONE.name
        }
    }
}