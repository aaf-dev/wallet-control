package ru.pinevpple.walletcontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_income_add.*
import ru.pinevpple.walletcontrol.models.IncomeTable
import ru.pinevpple.walletcontrol.viewmodels.IncomeAddViewModel

class IncomeAddActivity : AppCompatActivity() {

    lateinit var viewModel: IncomeAddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_add)
        initViewModel()
        initClickListeners()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(IncomeAddViewModel::class.java)
    }

    private fun initClickListeners() {
        btn_done.setOnClickListener {
            if (et_amount.text.isBlank()) {
                et_amount.error = "Error message!"
            } else {
                val amount = et_amount.text.toString().toInt()
                val date = "07.12.2019" // TODO() et_date.text.toString()

                val income = IncomeTable(amount, date)
                viewModel.insertIncome(income)
                finish()
            }
        }
    }
}