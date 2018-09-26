package io.ztc.pickerscrollview.TimePickerScrollView;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import io.ztc.pickerscrollview.PickerScrollView.PickerScrollView;
import io.ztc.pickerscrollview.PickerScrollView.Pickers;
import io.ztc.pickerscrollview.R;

public class DatePickerScrollView extends RelativeLayout {

    private Button ok_btn;
    private Button cancel_btn;
    private PickerScrollView yearTimePickerScrollView;
    private PickerScrollView monthTimePickerScrollView;
    private PickerScrollView dayTimePickerScrollView;
    private OnOkBtnListener onOkBtnListener;
    private OnCancelBtnListener onCancelBtnListener;

    private List<Pickers> monthList;
    private String[] monthId;
    private String[] monthName;

    private List<Pickers> dayList;
    private String[] dayId;
    private String[] dayName;

    private List<Pickers> yearList;
    private String[] yearId;
    private String[] yearName;

    private static String select_year;
    private static String select_month;
    private static String select_day;

    public static String getSelect_year() {
        return select_year;
    }

    public static void setSelect_year(String select_year) {
        DatePickerScrollView.select_year = select_year;
    }

    public static String getSelect_month() {
        return select_month;
    }

    public static void setSelect_month(String select_month) {
        DatePickerScrollView.select_month = select_month;
    }

    public static String getSelect_day() {
        return select_day;
    }

    public static void setSelect_day(String select_day) {
        DatePickerScrollView.select_day = select_day;
    }


    /**
     * 闰年判断标记
     */
    boolean leap_year = false;
    public static int monthType;
    private final int BIG_MONTH = 1;
    private final int SMALL_MONTH = 2;
    private final int NORMAL_FEBRUARY = 3;
    private final int LEAP_FEBRUARY = 4;

    Calendar calendar;//获取时间

    public DatePickerScrollView(Context context) {
        super(context);
        initView();
        initDate();
    }

    public DatePickerScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        initDate();
    }

    public DatePickerScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initDate();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DatePickerScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
        initDate();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.date_picker_scroll_view,null);
        yearTimePickerScrollView = view.findViewById(R.id.pick_years_view);
        monthTimePickerScrollView = view.findViewById(R.id.pick_month_view);
        dayTimePickerScrollView = view.findViewById(R.id.pick_day_view);
        ok_btn = view.findViewById(R.id.ok_btn);
        cancel_btn = view.findViewById(R.id.cancel_btn);
        this.addView(view);
    }

    private void initDate() {
        calendar = Calendar.getInstance();

        select_year = calendar.get(Calendar.YEAR)+"";
        if (calendar.get(Calendar.MONTH)<10){
            select_month = "0"+(calendar.get(Calendar.MONTH)+1);
        }else {
            select_month = ""+(calendar.get(Calendar.MONTH)+1);
        }
        if (calendar.get(Calendar.DAY_OF_MONTH)<10){
            select_day = "0"+calendar.get(Calendar.DAY_OF_MONTH);
        }else {
            select_day = ""+calendar.get(Calendar.DAY_OF_MONTH);
        }
        /**
         * 月份数据填充
         */
        monthList = new ArrayList<>();
        monthId = new String[] { "01", "02", "03", "04", "05", "06","07","08","09","10","11","12" };
        monthName = new String[] { "01月", "02月", "03月", "04月", "05月", "06月", "07月", "08月", "09月", "10月", "11月", "12月" };
        for (int i = 0; i < monthName.length; i++) {
            monthList.add(new Pickers(monthName[i], monthId[i]));
        }
        monthTimePickerScrollView.setData(monthList);
        /**
         * 日期数据填充
         */
        dayList = new ArrayList<>();
        int year = 0;
        if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){

        }else{

        }
        dayId = new String[] { "01", "02", "03", "04", "05", "06","07","08","09","10"
                ,"11","12","13","14","15","16","17","18","19","20"
                ,"21","22","23","24","25","26","27","28","29","30","31"};
        dayName = new String[] { "01日", "02日", "03日", "04日", "05日", "06日","07日","08日","09日","10日"
                ,"11日","12日","13日","14日","15日","16日","17日","18日","19日","20日"
                ,"21日","22日","23日","24日","25日","26日","27日","28日","29日","30日","31日" };
        for (int i = 0; i < dayName.length; i++) {
            dayList.add(new Pickers(dayName[i], dayId[i]));
        }
        dayTimePickerScrollView.setData(dayList);
        /**
         * 年份数据填充
         */
        yearList = new ArrayList<>();
        int now_Year = calendar.get(Calendar.YEAR); // 获取当前年份
        Log.i("当前年份",now_Year+"");
        List<String> yearIdList = new ArrayList<>();
        List<String> yearNameList = new ArrayList<>();
        int addListSize = 100;
        for (int i = 0;i<addListSize*2;i++){
           yearIdList.add(String.valueOf(now_Year-addListSize+i));
        }
        yearId = yearIdList.toArray(new String[(yearIdList.size())]);
        Log.i("yearID", Arrays.toString(yearId));

        for (int i = 0;i<addListSize*2;i++){
            yearNameList.add(now_Year-addListSize+i+"年");
        }
        yearName = new String[(yearNameList.size())];
        yearNameList.toArray(yearName);
        for (int i = 0; i < yearName.length; i++) {
            yearList.add(new Pickers(yearName[i], yearId[i]));
        }
        yearTimePickerScrollView.setData(yearList);
        /**
         * 年份滑动判断
         */
        yearTimePickerScrollView.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(Pickers pickers) {
                try {
                    Log.i("MONTH",getSelect_month()+"");
                    String now_select_year = pickers.getShowId();
                    if ( Integer.parseInt(now_select_year) % 4 == 0 && Integer.parseInt(now_select_year) % 100 != 0 || Integer.parseInt(now_select_year) % 400 == 0){
                        leap_year = true;
                        if ("02".equals(getSelect_month())){
                            setDayData(LEAP_FEBRUARY);
                            Log.i("RDAY","重构日期列表");
                        }
                    }else {
                        leap_year = false;
                        if ("02".equals(getSelect_month())){
                            setDayData(NORMAL_FEBRUARY);
                        }
                    }
                    setSelect_year(now_select_year);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        /**
         * 月份滑动判断
         */
        monthTimePickerScrollView.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(Pickers pickers) {
                try {
                    String now_select_month = pickers.getShowId();
                    int now_select_m =  Integer.parseInt(now_select_month);
                    if (now_select_m == 1||now_select_m == 3||now_select_m == 5||now_select_m == 7||now_select_m == 8||now_select_m == 10||now_select_m == 12){
                        setDayData(BIG_MONTH);
                    }else if (now_select_m == 2 && leap_year == true){
                        setDayData(LEAP_FEBRUARY);
                    }else if(now_select_m == 2 && leap_year == false){
                        setDayData(NORMAL_FEBRUARY);
                    }else if(now_select_m == 4||now_select_m == 6||now_select_m == 9||now_select_m == 11){
                        setDayData(SMALL_MONTH);
                    }
                    setSelect_month(now_select_month);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        /**
         *
         */
        dayTimePickerScrollView.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(Pickers pickers) {
                String now_select_day = pickers.getShowId();
                setSelect_day(now_select_day);
            }
        });
        /**
         * 初始化显示位置
         */
        yearTimePickerScrollView.setSelected(addListSize);
        //Log.i("确定当前坐标",calendar.get(Calendar.YEAR)-1+"");
        monthTimePickerScrollView.setSelected(calendar.get(Calendar.MONTH));
        dayTimePickerScrollView.setSelected(calendar.get(Calendar.DAY_OF_MONTH)-1);
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

    /**
     * 根据状态编辑日期
     * @param monthType
     */
    public void setDayData(int monthType){
        switch (monthType){
            case BIG_MONTH:
                dayList.clear();
                for (int i = 0; i < dayName.length; i++) {
                    dayList.add(new Pickers(dayName[i], dayId[i]));
                }
                dayTimePickerScrollView.setData(dayList);
                dayTimePickerScrollView.setSelected(15);
                setSelect_day(16+"");
                break;
            case SMALL_MONTH:
                dayList.clear();
                for (int i = 0; i < dayName.length-1; i++) {
                    dayList.add(new Pickers(dayName[i], dayId[i]));
                }
                dayTimePickerScrollView.setData(dayList);
                dayTimePickerScrollView.setSelected(15);
                setSelect_day(16+"");
                break;
            case NORMAL_FEBRUARY:
                dayList.clear();
                for (int i = 0; i < dayName.length-3; i++) {
                    dayList.add(new Pickers(dayName[i], dayId[i]));
                }
                dayTimePickerScrollView.setData(dayList);
                dayTimePickerScrollView.setSelected(15);
                setSelect_day(16+"");
                break;
            case LEAP_FEBRUARY:
                dayList.clear();
                for (int i = 0; i < dayName.length-2; i++) {
                    dayList.add(new Pickers(dayName[i], dayId[i]));
                }
                dayTimePickerScrollView.setData(dayList);
                dayTimePickerScrollView.setSelected(15);
                setSelect_day(16+"");
                break;
        }

    }

    public void setOnOkBtnListener(OnOkBtnListener onOkBtnListener){
        this.onOkBtnListener = onOkBtnListener;
    }
    public interface OnOkBtnListener{
        void onOkBtn();
    }

    public void setOnCancelBtnListener(OnCancelBtnListener onCancelBtnListener){
        this.onCancelBtnListener = onCancelBtnListener;
    }
    public interface OnCancelBtnListener{
        void onCancelBtn();
    }
}
