package com.stephen.mokitotutorial.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Written by StephenLeeDev on 2022/01/19.
 */

@Entity
data class ToDoModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val hasCompleted: Boolean = false,
)