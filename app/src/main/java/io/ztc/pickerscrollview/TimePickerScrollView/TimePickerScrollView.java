package io.ztc.pickerscrollview.TimePickerScrollView;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.ztc.pickerscrollview.PickerScrollView.PickerScrollView;
import io.ztc.pickerscrollview.PickerScrollView.Pickers;
import io.ztc.pickerscrollview.R;

public class TimePickerScrollView extends RelativeLayout {

    Calendar calendar;//获取时间

    private Button ok_btn;
    private Button cancel_btn;
    private PickerScrollView hoursTimePickerScrollView;
    private PickerScrollView minutesTimePickerScrollView;
    private PickerScrollView secondsTimePickerScrollView;
    private OnOkBtnListener onOkBtnListener;
    private OnCancelBtnListener onCancelBtnListener;

    private List<Pickers> hoursList;
    private List<Pickers> minutesList;
    private List<Pickers> secondsList;

    private static String select_hour;
    private static String select_minute;
    private static String select_second;

    public static String getSelect_hour() {
        return select_hour;
    }

    public static void setSelect_hour(String select_hour) {
        TimePickerScrollView.select_hour = select_hour;
    }

    public static String getSelect_minute() {
        return select_minute;
    }

    public static void setSelect_minute(String select_minute) {
        TimePickerScrollView.select_minute = select_minute;
    }

    public static String getSelect_second() {
        return select_second;
    }

    public static void setSelect_second(String select_second) {
        TimePickerScrollView.select_second = select_second;
    }

    public TimePickerScrollView(Context context) {
        super(context);
        initView();
        initData();
    }

    public TimePickerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initData();
    }

    public TimePickerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initData();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TimePickerScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
        initData();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.time_picker_scroll_view,null);
        hoursTimePickerScrollView = view.findViewById(R.id.pick_hours_view);
        minutesTimePickerScrollView = view.findViewById(R.id.pick_minutes_view);
        secondsTimePickerScrollView = view.findViewById(R.id.pick_seconds_view);
        ok_btn = view.findViewById(R.id.ok_btn);
        cancel_btn = view.findViewById(R.id.cancel_btn);
        this.addView(view);
    }
    private void initData() {
        calendar = Calendar.getInstance();
        if (calendar.get(Calendar.HOUR_OF_DAY)<10){
            select_hour = "0"+calendar.get(Calendar.HOUR_OF_DAY);
        }else {
            select_hour = ""+calendar.get(Calendar.HOUR_OF_DAY);
        }
        if (calendar.get(Calendar.MINUTE)<10){
            select_minute = "0"+calendar.get(Calendar.MINUTE);
        }else {
            select_minute = ""+calendar.get(Calendar.MINUTE);
        }
        if (calendar.get(Calendar.SECOND)<10){
            select_second = "0"+calendar.get(Calendar.SECOND);
        }else {
            select_second = ""+calendar.get(Calendar.SECOND);
        }
        /**
         * 小时数据填充
         */
        hoursList = new ArrayList<>();
        List<String> hoursIdList = new ArrayList<>();
        List<String> hoursNameList = new ArrayList<>();
        for (int i = 0;i<24;i++){
            if (i<10){
                hoursIdList.add("0"+i);
            }else {
                hoursIdList.add(String.valueOf(i));
            }
        }
        String[] hoursId = hoursIdList.toArray(new String[24]);
        for (int i = 0;i<24;i++){

            if (i<10){
                hoursNameList.add("0"+i+"点");
            }else {
                hoursNameList.add(i+"点");
            }
        }
        String[] hoursName = new String[(hoursNameList.size())];
        hoursNameList.toArray(hoursName);
        for (int i = 0; i < hoursName.length; i++) {
            hoursList.add(new Pickers(hoursName[i], hoursId[i]));
        }
        /**
         * 分钟数据填充
         */
        minutesList = new ArrayList<>();
        List<String> minutesIdList = new ArrayList<>();
        List<String> minutesNameList = new ArrayList<>();
        for (int i = 0;i<60;i++){
            if (i<10){
                minutesIdList.add("0"+i);
            }else {
                minutesIdList.add(String.valueOf(i));
            }

        }
        String[] minutesId = minutesIdList.toArray(new String[24]);
        for (int i = 0;i<60;i++){
            if (i<10){
                minutesNameList.add("0"+i+"分");
            }else {
                minutesNameList.add(i+"分");
            }
        }
        String[] minutesName = new String[(minutesNameList.size())];
        minutesNameList.toArray(minutesName);
        for (int i = 0; i < minutesName.length; i++) {
            minutesList.add(new Pickers(minutesName[i], minutesId[i]));
        }
        /**
         * 秒数据填充
         */
        secondsList = new ArrayList<>();
        List<String> secondsIdList = new ArrayList<>();
        List<String> secondsNameList = new ArrayList<>();
        for (int i = 0;i<60;i++){
            if (i<10){
                secondsIdList.add("0"+i);
            }else {
                secondsIdList.add(String.valueOf(i));
            }
        }
        String[] secondsId = secondsIdList.toArray(new String[24]);
        for (int i = 0;i<60;i++){
            if (i<10){
                secondsNameList.add("0"+i+"秒");
            }else {
                secondsNameList.add(i+"秒");
            }

        }
        String[] secondsName = new String[(secondsNameList.size())];
        secondsNameList.toArray(secondsName);
        for (int i = 0; i < secondsName.length; i++) {
            secondsList.add(new Pickers(secondsName[i], secondsId[i]));
        }
        /**
         * 填充数据
         */
        hoursTimePickerScrollView.setData(hoursList);
        hoursTimePickerScrollView.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(Pickers pickers) {
                try {
                    setSelect_hour(pickers.getShowId());
                }catch  (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        minutesTimePickerScrollView.setData(minutesList);
        minutesTimePickerScrollView.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(Pickers pickers) {
                try {
                    setSelect_minute(pickers.getShowId());
                }catch  (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        secondsTimePickerScrollView.setData(secondsList);
        secondsTimePickerScrollView.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(Pickers pickers) {
                try {
                    setSelect_second(pickers.getShowId());
                }catch  (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        /**
         * 设置默认位置
         */
        /**/
        hoursTimePickerScrollView.setSelected(calendar.get(Calendar.HOUR_OF_DAY));
        minutesTimePickerScrollView.setSelected(calendar.get(Calendar.MINUTE));
        secondsTimePickerScrollView.setSelected(calendar.get(Calendar.SECOND));

        /**
         * 保存按钮
         */
        ok_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOkBtnListener != null){
                    onOkBtnListener.onOkBtn();
                }
            }
        });
        /**
         * 取消按钮
         */
        cancel_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelBtnListener != null){
                    onCancelBtnListener.onCancelBtn();
                }
            }
        });
    }

    public void hideSecond(){
        secondsTimePickerScrollView.setVisibility(GONE);
    }


    public void setOnOkBtnListener(TimePickerScrollView.OnOkBtnListener onOkBtnListener){
        this.onOkBtnListener = onOkBtnListener;
    }
    public interface OnOkBtnListener{
        void onOkBtn();
    }

    public void setOnCancelBtnListener(TimePickerScrollView.OnCancelBtnListener onCancelBtnListener){
        this.onCancelBtnListener = onCancelBtnListener;
    }
    public interface OnCancelBtnListener{
        void onCancelBtn();
    }
}
