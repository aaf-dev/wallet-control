package ru.pinevpple.walletcontrol.main.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.bills_list_template.view.*
import ru.pinevpple.walletcontrol.income.ui.AddIncomeFragment
import ru.pinevpple.walletcontrol.R
import ru.pinevpple.walletcontrol.db.model.BillsTable
import ru.pinevpple.walletcontrol.main.model.MainData
import ru.pinevpple.walletcontrol.main.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var addIncomeFragment: AddIncomeFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initClickListeners()
        main_fragment_root.smoothScrollTo(0, 500)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.mediator.observe(this, Observer { })
        viewModel.infoData.observe(this, Observer { updateData(it) })
        viewModel.billsList?.observe(this, Observer { createCard(it) })
    }

    private fun updateData(info: MainData?) {
        val income = info?.getIncomeString() ?: "0"
        val expense = info?.getExpenseString() ?: "0"
        val balance = info?.getBalanceString() ?: "0"
        tv_income_value.text = income
        tv_expense_value.text = expense
        tv_balance_value.text = balance
    }

    private fun initClickListeners() {
        btn_add_income.setOnClickListener {
            startAddIncomeFragment()
        }
    }

    private fun createCard(list: List<BillsTable>) {
        val quantity = list.size
        ll_cards_container.setPadding(16)
        if (quantity == 0) {
            val layout = layoutInflater.inflate(R.layout.bills_list_empty_template, null)
            ll_cards_container.addView(layout, 0)
        } else {
            for (i in 0 until quantity) {
                val layout = layoutInflater.inflate(R.layout.bills_list_template, null)
                layout.tv_bill_name.text = list[i].title
                layout.tv_bill_description.text = list[i].type.label
                layout.tv_bill_balance.text = list[i].balance.toString()
                layout.iv_bill_image.setImageResource(R.drawable.ic_launcher_background)

                ll_cards_container.addView(layout, i)
            }
        }
    }

    private fun startAddIncomeFragment() {
        Log.d("M_Fragments", "started second fragment")
        addIncomeFragment =
            AddIncomeFragment()
        val t: FragmentTransaction = fragmentManager!!.beginTransaction()
        t.setCustomAnimations(
            android.R.animator.fade_in,
            android.R.animator.fade_out,
            android.R.animator.fade_in,
            android.R.animator.fade_out
        )
        t.replace(R.id.root_container, addIncomeFragment, "fragment_add_income")
        t.setReorderingAllowed(true)
        t.addToBackStack(tag)
        t.commit()
    }
}