package ru.pinevpple.walletcontrol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.marginStart
import androidx.core.view.setPadding
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bills_list_template.view.*
import ru.pinevpple.walletcontrol.models.GeneralInfo
import ru.pinevpple.walletcontrol.viewmodels.MainViewModel

private const val INCOME = "INCOME"
private const val EXPENSE = "EXPENSE"
private const val BALANCE = "BALANCE"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var transaction: FragmentTransaction

    private val addIncome = AddIncomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initClickListeners()
        createCard(5)
    }

    private fun createCard(quantity: Int) {
        for (i in 0 .. quantity) {
            val layout = layoutInflater.inflate(R.layout.bills_list_template, null)
            val balance = i * 15 * 325 * 43

            ll_cards_container.setPadding(16)

            layout.tv_bill_name.text = "Test $i"
            layout.tv_bill_description.text = "Description $i"
            layout.tv_bill_balance.text = "$balance"
            layout.iv_bill_image.setImageResource(R.drawable.ic_launcher_background)

            ll_cards_container.addView(layout, i)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.mediator.observe(this, Observer { })
        viewModel.infoData.observe(this, Observer { updateData(it) })

    }

    private fun updateData(info: GeneralInfo?) {
        val income = info?.getIncomeString() ?: "0"
        val expense = info?.getExpenseString() ?: "0"
        val balance = info?.getBalanceString() ?: "0"

        tv_income_value.text = income
        tv_expense_value.text = expense
        tv_balance_value.text = balance
    }

    private fun initClickListeners() {
        transaction = supportFragmentManager.beginTransaction()

        btn_add_income.setOnClickListener {
            main_container.visibility = View.GONE // TODO() EDITED
            transaction.replace(R.id.fragment_container, addIncome) // TODO() EDITED
            transaction.addToBackStack(null) // TODO() EDITED
            transaction.commit()
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