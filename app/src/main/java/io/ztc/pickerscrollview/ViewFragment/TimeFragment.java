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
import io.ztc.pickerscrollview.TimePickerScrollView.TimePickerScrollView;

public class TimeFragment extends BaseFragment implements View.OnClickListener,TimePickerScrollView.OnOkBtnListener,TimePickerScrollView.OnCancelBtnListener{
    private Button time_hide_btn;
    public OnHideListener hidelistener;
    private TimePickerScrollView timePickerScrollView;
    private String hours;
    private String minutes;
    private String seconds;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time,null);
        time_hide_btn = view.findViewById(R.id.time_hide_btn);
        timePickerScrollView = view.findViewById(R.id.time_view);
        return view;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initData() {
        time_hide_btn.setOnClickListener(this);
        timePickerScrollView.hideSecond();//隐藏秒选项
        timePickerScrollView.setOnOkBtnListener(this);
        timePickerScrollView.setOnCancelBtnListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.time_hide_btn) {
            if (hidelistener != null) {
                hidelistener.OnHideViewTimeView();
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
        hours = TimePickerScrollView.getSelect_hour();
        minutes = TimePickerScrollView.getSelect_minute();
        seconds = TimePickerScrollView.getSelect_second();
        if (hidelistener != null){
            hidelistener.OnPutShowTime(hours,minutes,"00");
            hidelistener.OnHideViewTimeView();
        }
    }

    @Override
    public void onCancelBtn() {
        if(hidelistener != null){
            hidelistener.OnHideViewTimeView();
        }
    }

    public interface OnHideListener{
        void OnHideViewTimeView();
        void OnPutShowTime(String hours, String minutes, String seconds);
    }

}
