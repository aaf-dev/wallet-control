package ru.pinevpple.walletcontrol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import ru.pinevpple.walletcontrol.models.GeneralInfo
import ru.pinevpple.walletcontrol.viewmodels.MainViewModel

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
        viewModel.getInfo().observe(this, Observer { updateData(it) })
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

    private fun updateData(info: GeneralInfo) {
        tv_income_value.text = info.income.toString()
        tv_expense_value.text = info.expense.toString()
        tv_balance_value.text = info.balance.toString()
    }
}