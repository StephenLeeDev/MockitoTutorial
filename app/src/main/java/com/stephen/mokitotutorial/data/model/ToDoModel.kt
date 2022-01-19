package com.stephen.mokitotutorial.data.model

/**
 * Written by StephenLeeDev on 2022/01/19.
 */

data class ToDoModel(
    val id: Long = 0,
    val title: String,
    val description: String,
    val hasCompleted: Boolean,
)