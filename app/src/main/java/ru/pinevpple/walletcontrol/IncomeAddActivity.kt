package ru.pinevpple.walletcontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_add_income.*
import ru.pinevpple.walletcontrol.models.IncomeTable
import ru.pinevpple.walletcontrol.viewmodels.AddIncomeVM

class IncomeAddActivity : AppCompatActivity() {

    private lateinit var viewModel: AddIncomeVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_income)
        initViewModel()
        initClickListeners()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(AddIncomeVM::class.java)
    }

    private fun initClickListeners() {

        et_date.setOnClickListener {
        }

        btn_done.setOnClickListener {
            if (et_amount.text.isBlank()) {
                et_amount.error = "Error message!"
            } else {
                val amount = et_amount.text.toString().toFloat()
                val date = "date TODO"

                val income = IncomeTable(amount, date)
                viewModel.insertIncome(income)
                finish()
            }
        }
    }
}