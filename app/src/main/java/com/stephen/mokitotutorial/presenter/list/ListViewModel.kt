package com.stephen.mokitotutorial.presenter.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stephen.mokitotutorial.data.model.ToDoModel
import com.stephen.mokitotutorial.domain.todo.DeleteAllToDoItemUseCase
import com.stephen.mokitotutorial.domain.todo.GetToDoListUseCase
import com.stephen.mokitotutorial.domain.todo.UpdateToDoUseCase
import com.stephen.mokitotutorial.presenter.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Written by StephenLeeDev on 2022/01/26.
 */

internal class ListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
    private val deleteAllToDoItemUseCase: DeleteAllToDoItemUseCase
) : BaseViewModel() {

    private var _toDoListLiveData = MutableLiveData<ToDoListState>(ToDoListState.UnInitialized)
    val toDoListLiveData: LiveData<ToDoListState> = _toDoListLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _toDoListLiveData.postValue(ToDoListState.Loading)
        _toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
    }

    fun updateEntity(toDoModel: ToDoModel) = viewModelScope.launch {
        updateToDoUseCase(toDoModel)
    }

    fun deleteAll() = viewModelScope.launch {
        _toDoListLiveData.postValue(ToDoListState.Loading)
        deleteAllToDoItemUseCase()
        _toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
    }
}