package ru.pinevpple.walletcontrol.main.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.bills_list_template.view.*
import ru.pinevpple.walletcontrol.income.ui.AddIncomeFragment
import ru.pinevpple.walletcontrol.R
import ru.pinevpple.walletcontrol.db.model.BillsTable
import ru.pinevpple.walletcontrol.main.model.MainData
import ru.pinevpple.walletcontrol.main.model.Operation
import ru.pinevpple.walletcontrol.main.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var addIncomeFragment: AddIncomeFragment

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, parent, false)

    override fun onActivityCreated(state: Bundle?) {
        super.onActivityCreated(state)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainDataSubscribe()
        listOfBillsSubscribe()
        listOfOperationsSubscribe()
        initClickListeners()
        main_fragment_root.smoothScrollTo(0, 500)
    }

    private fun mainDataSubscribe() {
        viewModel.mainDataTest
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(AndroidLifecycleScopeProvider.from(viewLifecycleOwner))
            .subscribe (
                { data -> updateData(data) },
                { error -> throw error }
            )
    }

    private fun updateData(data: MainData) {
        tv_income_value.text = data.getIncomeString()
        tv_expense_value.text = data.getExpenseString()
        tv_balance_value.text = data.getBalanceString()
    }

    private fun listOfBillsSubscribe() {
        viewModel.getListOfBills()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(AndroidLifecycleScopeProvider.from(viewLifecycleOwner))
            .subscribe { bills -> updateBillsList(bills) }
    }

    @SuppressLint("InflateParams")
    private fun updateBillsList(list: List<BillsTable>) {
        ll_cards_container.setPadding(16)
        if (list.isNotEmpty()) {
            list.forEach { bill -> createCard(bill) }
        } else {
            val layout = layoutInflater.inflate(R.layout.bills_list_empty_template, null)
            ll_cards_container.addView(layout, 0)
        }
    }

    @SuppressLint("InflateParams")
    private fun createCard(bill: BillsTable) {
        val layout = layoutInflater.inflate(R.layout.bills_list_template, null)
        layout.tv_bill_name.text = bill.title
        layout.tv_bill_description.text = bill.type
        layout.tv_bill_balance.text = bill.balance.toString()
        layout.iv_bill_image.setImageResource(R.drawable.ic_launcher_background)
        ll_cards_container.addView(layout, bill.getId())
    }

    private fun listOfOperationsSubscribe() {
        viewModel.getListOfIncomesAndExpenses()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(AndroidLifecycleScopeProvider.from(viewLifecycleOwner))
            .subscribe { operations -> updateList(operations) }
    }

    private fun updateList(operations: List<Operation>) {
        Log.d("", "Operation list size: ${operations.size}")
    }

    private fun initClickListeners() {
        btn_add_income.setOnClickListener {
            startAddIncomeFragment()
        }
    }

    private fun startAddIncomeFragment() {
        Log.d("M_Fragments", "started second fragment")
        addIncomeFragment =
            AddIncomeFragment()
        fragmentManager!!.beginTransaction()
            .setCustomAnimations(
                R.animator.slide_in_right,
                R.animator.slide_out_right,
                R.animator.slide_in_right,
                R.animator.slide_out_right
            )
            .replace(R.id.root_container, addIncomeFragment, AddIncomeFragment::class.java.name)
            .setReorderingAllowed(true)
            .addToBackStack(tag)
            .commit()
    }
}