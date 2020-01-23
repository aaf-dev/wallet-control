package ru.pinevpple.walletcontrol.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import ru.pinevpple.walletcontrol.models.Transfer
import java.util.*

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val timePicker: TimePickerDialogFragment =
        TimePickerDialogFragment()
    private val c = Calendar.getInstance()

    private lateinit var transfer: Transfer

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity!!, this, year, month, day)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        transfer = targetFragment as Transfer
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        c.set(year, month, dayOfMonth)
        transfer.transferDate(c.time)
        timePicker.setTargetFragment(targetFragment, 1)
        timePicker.show(fragmentManager!!, "timePicker")
    }
}