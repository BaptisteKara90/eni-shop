package fr.eni.ecole.eni_shop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import fr.eni.ecole.eni_shop.repository.UserPreferenceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel( private val userPreferencesRepository: UserPreferenceRepository) : ViewModel() {

    private val _isDarkTheme = MutableStateFlow(false)

    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    fun toggleDarkTheme() {
        _isDarkTheme.value = !_isDarkTheme.value

        viewModelScope.launch {
            userPreferencesRepository.setDarkThemeSelected(_isDarkTheme.value)
        }
    }

    init{
        getDarkTheme()
    }

    fun getDarkTheme() {
        viewModelScope.launch {
            userPreferencesRepository.getDarkThemeSelected().collect{
                _isDarkTheme.value = it
            }
        }
    }

    fun setDarkTheme(value: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setDarkThemeSelected(value)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return SettingsViewModel(UserPreferenceRepository(application.applicationContext)) as T
            }
        }
    }

}