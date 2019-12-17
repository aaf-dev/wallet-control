package ru.pinevpple.walletcontrol

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
import ru.pinevpple.walletcontrol.models.GeneralInfo
import ru.pinevpple.walletcontrol.viewmodels.MainViewModel

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var addIncomeFragment: AddIncomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("M_fragments", "fragment_main started")
    }

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
        createCard(5)
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
        btn_add_income.setOnClickListener {
            startAddIncomeFragment()
        }
    }

    private fun createCard(quantity: Int) {
        for (i in 0 until quantity) {
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

    private fun startAddIncomeFragment() {
        addIncomeFragment = AddIncomeFragment()
        val t: FragmentTransaction = fragmentManager!!.beginTransaction()
        t.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
        t.replace(R.id.root_container, addIncomeFragment, "fragment_add_income")
        t.setReorderingAllowed(true)
        t.addToBackStack(tag)
        t.commit()
        Log.d("M_WC", "started second fragment")
    }


}