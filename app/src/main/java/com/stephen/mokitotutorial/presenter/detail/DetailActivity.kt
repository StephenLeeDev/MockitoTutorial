package com.stephen.mokitotutorial.presenter.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isGone
import com.stephen.mokitotutorial.databinding.ActivityDetailBinding
import com.stephen.mokitotutorial.presenter.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class DetailActivity : BaseActivity<DetailViewModel>() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override val viewModel: DetailViewModel by viewModel {
        parametersOf(
            intent.getSerializableExtra(DETAIL_MODE_KEY),
            intent.getLongExtra(TODO_ID_KEY, -1)
        )
    }

    companion object {
        const val TODO_ID_KEY = "ToDoId"
        const val DETAIL_MODE_KEY = "DetailMode"

        const val FETCH_REQUEST_CODE = 10

        fun getIntent(context: Context, detailMode: DetailMode) = Intent(context, DetailActivity::class.java).apply {
            putExtra(DETAIL_MODE_KEY, detailMode)
        }

        fun getIntent(context: Context, id: Long, detailMode: DetailMode) = Intent(context, DetailActivity::class.java).apply {
            putExtra(TODO_ID_KEY, id)
            putExtra(DETAIL_MODE_KEY, detailMode)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setResult(Activity.RESULT_OK)
    }

    override fun observeData() = viewModel.toDoDetailLiveData.observe(this@DetailActivity) {
        when (it) {
            is ToDoDetailState.UnInitialized -> {
                initViews(binding)
            }
            is ToDoDetailState.Loading -> {
                handleLoadingState()
            }
            is ToDoDetailState.Success -> {
                handleSuccessState(it)
            }
            is ToDoDetailState.Modify -> {
                handleModifyState()
            }
            is ToDoDetailState.Delete -> {
                Toast.makeText(this, "성공적으로 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
            is ToDoDetailState.Error -> {
                Toast.makeText(this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
            is ToDoDetailState.Write -> {
                handleWriteState()
            }
        }
    }

    private fun initViews(binding: ActivityDetailBinding) = with(binding) {
        titleEditText.isEnabled = false
        descriptionEditText.isEnabled = false

        deleteButton.isGone = true
        modifyButton.isGone = true
        updateButton.isGone = true

        deleteButton.setOnClickListener {
            viewModel.deleteToDo()
        }
        modifyButton.setOnClickListener {
            viewModel.setModifyMode()
        }
        updateButton.setOnClickListener {
            viewModel.writeToDo(
                title = titleEditText.text.toString(),
                description = descriptionEditText.text.toString()
            )
        }
    }

    private fun handleLoadingState() = with(binding) {
        progressBar.isGone = false
    }

    private fun handleModifyState() = with(binding) {
        titleEditText.isEnabled = true
        descriptionEditText.isEnabled = true

        deleteButton.isGone = true
        modifyButton.isGone = true
        updateButton.isGone = false
    }

    private fun handleWriteState() = with(binding) {
        titleEditText.isEnabled = true
        descriptionEditText.isEnabled = true

        updateButton.isGone = false
    }

    private fun handleSuccessState(state: ToDoDetailState.Success) = with(binding) {
        progressBar.isGone = true

        titleEditText.isEnabled = false
        descriptionEditText.isEnabled = false

        deleteButton.isGone = false
        modifyButton.isGone = false
        updateButton.isGone = true

        val toDoItem = state.toDoItem
        titleEditText.setText(toDoItem.title)
        descriptionEditText.setText(toDoItem.description)
    }

}