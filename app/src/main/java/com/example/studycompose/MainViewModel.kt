package com.example.studycompose

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ContentRepositoryImpl
) : ViewModel() {
    val allContent: Flow<List<Content>> = repository.getAll()
}
