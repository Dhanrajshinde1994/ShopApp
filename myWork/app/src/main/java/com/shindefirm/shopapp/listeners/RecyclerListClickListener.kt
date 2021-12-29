package com.shindefirm.shopapp.listeners

import android.widget.CompoundButton

interface RecyclerListClickListener {
    fun onListItemClickListener(position: Int, holder: Any?, `object`: Any?)
    fun onSwitchCheckedChanged(
        buttonView: CompoundButton?, isChecked: Boolean,
        position: Int, `object`: Any?
    )
}