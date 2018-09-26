package io.ztc.pickerscrollview.ViewFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import io.ztc.pickerscrollview.BaseFragment;
import io.ztc.pickerscrollview.R;
import io.ztc.pickerscrollview.TimePickerScrollView.DatePickerScrollView;

public class DateFragment extends BaseFragment implements View.OnClickListener,DatePickerScrollView.OnOkBtnListener,DatePickerScrollView.OnCancelBtnListener{
    private Button date_hide_btn;
    public OnHideListener hidelistener;
    private DatePickerScrollView datePickerScrollView;
    private String years;
    private String months;
    private String days;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date,null);
        date_hide_btn = view.findViewById(R.id.date_hide_btn);
        datePickerScrollView = view.findViewById(R.id.date_view);
        return view;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initData() {
        date_hide_btn.setOnClickListener(this);
        datePickerScrollView.setOnOkBtnListener(this);
        datePickerScrollView.setOnCancelBtnListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.date_hide_btn) {
            if (hidelistener != null) {
                hidelistener.OnHideViewDateView();
            }

        }
    }

    @Override
    public void onAttach(Context context) {
        // 这是为了保证Activity容器实现了用以回调的接口。如果没有，它会抛出一个异常。
        try {
            // 实例化接口
            hidelistener = (OnHideListener)context;
            Log.i("获取实现接口","已经获得实现接口");
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "必须实现该接口");
        }
        super.onAttach(context);
    }

    /**
     * 低版本兼容
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            if (activity instanceof OnHideListener) {
                hidelistener = (OnHideListener) activity;
            } else {
                throw new RuntimeException(activity.toString()
                        + "必须实现该接口");
            }
        }
        super.onAttach(activity);
    }

    @Override
    public void onOkBtn() {
        years = DatePickerScrollView.getSelect_year();
        months = DatePickerScrollView.getSelect_month();
        days = DatePickerScrollView.getSelect_day();
        if (hidelistener != null){
            hidelistener.OnPutShowDate(years,months,days);
            hidelistener.OnHideViewDateView();
        }
    }

    @Override
    public void onCancelBtn() {
        if(hidelistener != null){
            hidelistener.OnHideViewDateView();
        }
    }

    public interface OnHideListener{
        void OnHideViewDateView();
        void OnPutShowDate(String years, String months, String days);
    }

}
