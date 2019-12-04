package ru.pinevpple.walletcontrol.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.pinevpple.walletcontrol.models.GeneralInfo

class MainViewModel : ViewModel() {

    private val generalInfo = MutableLiveData<GeneralInfo>()

    fun getInfo(): LiveData<GeneralInfo> = generalInfo

    fun saveInfo(info: GeneralInfo) {
        generalInfo.value = info
    }
}