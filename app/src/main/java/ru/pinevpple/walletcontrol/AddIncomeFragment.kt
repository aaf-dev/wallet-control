package ru.pinevpple.walletcontrol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_add_income.*
import ru.pinevpple.walletcontrol.models.IncomeTable
import ru.pinevpple.walletcontrol.models.Transfer
import ru.pinevpple.walletcontrol.utils.Utils
import ru.pinevpple.walletcontrol.viewmodels.AddIncomeVM
import java.text.SimpleDateFormat
import java.util.*

class AddIncomeFragment : Fragment(), Transfer {

    private val datePicker = DatePickerDialogFragment()
    private val timePicker = TimePickerDialogFragment()

    private lateinit var viewModel: AddIncomeVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_income, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initClickListeners()
        initViews()
    }

    private fun initViews() {
        val (date, time) = Utils.currentDateAndTime()
        et_date.setText(date)
        et_time.setText(time)
    }


    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(AddIncomeVM::class.java)
    }

    private fun initClickListeners() {
        et_date.setOnClickListener {
            datePicker.setTargetFragment(this, 0)
        }

        et_time.setOnClickListener {
            timePicker.setTargetFragment(this, 1)
        }

        btn_done.setOnClickListener {
            val amount = et_amount.text.toString().toFloat()
            val date = Utils.formattedDate(et_date.text.toString(), et_time.text.toString())
            val income = IncomeTable(amount, date)
            viewModel.insertIncome(income)
        }
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