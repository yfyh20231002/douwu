package com.dazhukeji.douwu.ui.aty.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.base.BaseAty;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import butterknife.BindView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/15 17:37
 * 功能描述：
 */
public class CalendarAty extends BaseAty implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener,
        CalendarView.OnMonthChangeListener {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.monthTv)
    TextView monthTv;
    @BindView(R.id.yearTv)
    TextView yearTv;
    @BindView(R.id.calendarView)
    CalendarView calendarView;

    @Override
    public int getLayoutId() {
        return R.layout.layout_calendar;
    }

    @Override
    public void initView() {
        txtTitle.setText("课程表");
        setYearTv(calendarView.getCurYear());
        setMonthTv(calendarView.getCurMonth());
        calendarView.setOnYearChangeListener(this::onYearChange);
        calendarView.setOnMonthChangeListener(this::onMonthChange);
        calendarView.setOnCalendarSelectListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onYearChange(int year) {
        setYearTv(year);
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        setMonthTv(calendar.getMonth());
        if (isClick) {
            Bundle bundle = new Bundle();
            bundle.putString("calendarDate",calendar.getYear()+"-"+calendar.getMonth()+"-"+calendar.getDay());
            startActivity(CourseAty.class,bundle);
        }
    }

    private static String getCalendarText(Calendar calendar) {
        return String.format("新历%s \n 农历%s \n 公历节日：%s \n 农历节日：%s \n 节气：%s \n 是否闰月：%s",
                calendar.getMonth() + "月" + calendar.getDay() + "日",
                calendar.getLunarCalendar().getMonth() + "月" + calendar.getLunarCalendar().getDay() + "日",
                TextUtils.isEmpty(calendar.getGregorianFestival()) ? "无" : calendar.getGregorianFestival(),
                TextUtils.isEmpty(calendar.getTraditionFestival()) ? "无" : calendar.getTraditionFestival(),
                TextUtils.isEmpty(calendar.getSolarTerm()) ? "无" : calendar.getSolarTerm(),
                calendar.getLeapMonth() == 0 ? "否" : String.format("闰%s月", calendar.getLeapMonth()));
    }

    @Override
    public void onMonthChange(int year, int month) {
        setMonthTv(month);
        setYearTv(year);

    }

    private void setYearTv(int year) {
        yearTv.setText(year + "\u0020年");
    }

    private void setMonthTv(int month) {
        monthTv.setText(month + "\u0020月");
    }

}
