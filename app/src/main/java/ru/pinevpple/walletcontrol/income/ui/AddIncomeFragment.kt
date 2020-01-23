package ru.pinevpple.walletcontrol.income.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_add_income.*
import ru.pinevpple.walletcontrol.R
import ru.pinevpple.walletcontrol.db.model.IncomeTable
import ru.pinevpple.walletcontrol.models.Transfer
import ru.pinevpple.walletcontrol.ui.DatePickerDialogFragment
import ru.pinevpple.walletcontrol.ui.TimePickerDialogFragment
import ru.pinevpple.walletcontrol.utils.Utils
import ru.pinevpple.walletcontrol.income.viewmodel.AddIncomeViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddIncomeFragment : Fragment(), Transfer {

    private val datePicker =
        DatePickerDialogFragment()
    private val timePicker =
        TimePickerDialogFragment()

    private lateinit var viewModel: AddIncomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_income, container, false)
        view.findViewById<EditText>(R.id.et_date).setText(Utils.currentDateAndTime().first)
        view.findViewById<EditText>(R.id.et_time).setText(Utils.currentDateAndTime().second)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initClickListeners()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(AddIncomeViewModel::class.java)
    }

    private fun initClickListeners() {
        et_date.setOnClickListener {
            datePicker.setTargetFragment(this, 0)
            datePicker.show(fragmentManager!!, "dialogfragment_datePicker")
        }

        et_time.setOnClickListener {
            timePicker.setTargetFragment(this, 1)
            timePicker.show(fragmentManager!!, "dialogfragment_timePicker")
        }

        btn_done.setOnClickListener {
            if (et_amount.text.isBlank()) {
                et_amount.error = "Error message!" // TODO() Create error message!
            } else {
                sendNewIncome()
                fragmentManager!!.popBackStack("fragment_main", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }
    }

    private fun sendNewIncome() {
        val amount = et_amount.text.toString().toFloat()
        val date = Utils.formattedDate(et_date.text.toString(), et_time.text.toString())
        val income =
            IncomeTable(amount, date)
        viewModel.insertIncome(income)
    }

    override fun transferDate(date: Date) {
        val displayFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(displayFormat, Locale.US).format(date)
        et_date.setText(sdf)
    }

    override fun transferTime(time: Date) {
        val displayFormat = "HH:mm"
        val sdf = SimpleDateFormat(displayFormat, Locale.US).format(time)
        et_time.setText(sdf)
    }
}