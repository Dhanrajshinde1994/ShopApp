package com.shindefirm.shopapp.listeners;

import android.widget.CompoundButton;

public interface RecyclerListClickListener {
    void onListItemClickListener(int position, Object holder, Object object);

    void onSwitchCheckedChanged(CompoundButton buttonView, boolean isChecked,
                                int position, Object object);


}
