package ru.pinevpple.walletcontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_income_add.*
import ru.pinevpple.walletcontrol.viewmodels.IncomeAddViewModel

class IncomeAddActivity : AppCompatActivity() {

    lateinit var viewModel: IncomeAddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_add)
        initViewModel()
        initClickListeners()
    }

    private fun initClickListeners() {
        btn_done.setOnClickListener {
            finish()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(IncomeAddViewModel::class.java)
    }
}