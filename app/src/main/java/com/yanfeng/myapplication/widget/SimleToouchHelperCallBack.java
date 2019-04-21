package com.yanfeng.myapplication.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class SimleToouchHelperCallBack extends ItemTouchHelper.Callback {
    private TouchCallBack mCallBack;
    private boolean mSwipeEnable=true;

    public SimleToouchHelperCallBack(TouchCallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int drag=ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipe=ItemTouchHelper.LEFT;

        return makeMovementFlags(drag,swipe);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        mCallBack.onItemMove(viewHolder.getAdapterPosition(),viewHolder1.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mCallBack.onItemDelete(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return mSwipeEnable;
    }

    public void setmSwipeEnable(boolean enable) {
        mSwipeEnable = enable;
    }
}
