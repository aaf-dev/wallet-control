package ru.pinevpple.walletcontrol

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import ru.pinevpple.walletcontrol.models.Transfer
import java.util.*

class TimePickerDialogFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private val c = Calendar.getInstance()
    private lateinit var transfer: Transfer

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        transfer = targetFragment as Transfer
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        c.set(Calendar.HOUR_OF_DAY, hourOfDay)
        c.set(Calendar.MINUTE, minute)
        transfer.transferTime(c.time)
    }
}