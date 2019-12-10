package ru.pinevpple.walletcontrol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import ru.pinevpple.walletcontrol.models.GeneralInfo
import ru.pinevpple.walletcontrol.viewmodels.MainViewModel

private const val INCOME = "INCOME"
private const val EXPENSE = "EXPENSE"
private const val BALANCE = "BALANCE"

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initClickListeners()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.mediator.observe(this, Observer {  })
        viewModel.infoData.observe(this, Observer { updateData(it) })

    }

    private fun updateData(info: GeneralInfo?) {
        val income = info?.getIncome() ?: 0
        val expense = info?.getExpense() ?: 0
        val balance = income - expense

        tv_income_value.text = income.toString()
        tv_expense_value.text = expense.toString()
        tv_balance_value.text = balance.toString()
    }

    private fun initClickListeners() {
        btn_add_income.setOnClickListener {
            val i = Intent(this@MainActivity, IncomeAddActivity::class.java)
            startActivity(i)
        }
        btn_add_expense.setOnClickListener {
            val i = Intent(this@MainActivity, IncomeAddActivity::class.java)
            startActivity(i)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val income = tv_income_value.text
        val expense = tv_expense_value.text
        val balance = tv_balance_value.text

        outState.putCharSequence(INCOME, income)
        outState.putCharSequence(EXPENSE, expense)
        outState.putCharSequence(BALANCE, balance)
    }
}