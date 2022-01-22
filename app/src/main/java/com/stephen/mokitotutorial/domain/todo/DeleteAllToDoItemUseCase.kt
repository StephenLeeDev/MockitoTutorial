package com.stephen.mokitotutorial.domain.todo

import com.stephen.mokitotutorial.data.repository.ToDoRepository
import com.stephen.mokitotutorial.domain.UseCase

internal class DeleteAllToDoItemUseCase(
    private val toDoRepository: ToDoRepository
): UseCase {

    suspend operator fun invoke() {
        return toDoRepository.deleteAll()
    }

}
