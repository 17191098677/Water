package com.smartcityin.waterknow.View.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.smartcityin.waterknow.Adapert.MyAnnualQueryItemAdapert;
import com.smartcityin.waterknow.Base.BaseFragment;
import com.smartcityin.waterknow.Base.ListViewForScrollView;
import com.smartcityin.waterknow.Entity.My.MyAnnualQueryEntity;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.MyAnnualQueryPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.View.Activity.MyAnnualQueryActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AnnualChildrenFragment extends BaseFragment<Commpart.MyAnnualQueryViewInf, MyAnnualQueryPresenter>  {

    @BindView(R.id.baseRingView)
    PieChart mChart;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.userWater_lv)
    ListViewForScrollView userWaterLv;
    private MyAnnualQueryActivity activity;
    private String url;
    private String token;
    private String meterID;
    private ArrayList<MyAnnualQueryEntity.DataBean.HierarchicalDataBean> mList=new ArrayList<>();
    private MyAnnualQueryItemAdapert adapert;

    @Override
    protected MyAnnualQueryPresenter createPresenter() {
        return new MyAnnualQueryPresenter();
    }

    @Override
    public int getLayoutId() {
        activity= (MyAnnualQueryActivity) getActivity();
        return R.layout.fragment_annual_children;
    }

    @Override
    protected void initAdapert() {
        adapert = new MyAnnualQueryItemAdapert(activity,mList);
        userWaterLv.setAdapter(adapert);
    }

    @Override
    protected void initListener() {

    }

    public void initData() {
        Bundle bundle = getArguments();
        url = bundle.getString("url", "");
        token = bundle.getString("token", "");
        meterID = bundle.getString("meterID", "");

        switch (url) {
            case 2016 + "":
                mPresenter = new MyAnnualQueryPresenter();
                mPresenter.setMyAnnualQuery(token, url, meterID);
                mPresenter.attarchView(new Commpart.MyAnnualQueryViewInf() {
                    @Override
                    public void getMyAnnualQueryInf(MyAnnualQueryEntity myAnnualQueryEntity) {
                        int code = myAnnualQueryEntity.getCode();
                        switch (code){
                            case 200:
                                MyAnnualQueryEntity.DataBean data = myAnnualQueryEntity.getData();
                                tvYear.setText("本年消费情况："+new DecimalFormat("##.##").format(data.getWater_rate())+"元");
                                List<MyAnnualQueryEntity.DataBean.ConstituteOfWaterRateBean> constitute_of_water_rate = data.getConstitute_of_water_rate();
                                ArrayList<String> list = new ArrayList<>();
                                ArrayList<Integer> strList = new ArrayList<>();
                                for (int i = 0; i < constitute_of_water_rate.size(); i++) {
                                    if (constitute_of_water_rate.get(i).getPercent()>0&&constitute_of_water_rate.get(i).getPercent()<=1){
                                        list.add(constitute_of_water_rate.get(i).getKey()+":"+constitute_of_water_rate.get(i).getVar()+"元");
                                        strList.add((int) (constitute_of_water_rate.get(i).getPercent()*100));
                                    }
                                }
                                setData(list,strList);
                                List<MyAnnualQueryEntity.DataBean.HierarchicalDataBean> hierarchical_data = data.getHierarchical_data();
                                mList.clear();
                                mList.addAll(hierarchical_data);
                                adapert.notifyDataSetChanged();
                                break;
                            case 501:
                                break;
                            case 502:
                                break;
                        }
                    }
                });
                break;
            case 2017 + "":
                mPresenter = new MyAnnualQueryPresenter();
                mPresenter.setMyAnnualQuery(token, url, meterID);
                mPresenter.attarchView(new Commpart.MyAnnualQueryViewInf() {
                    @Override
                    public void getMyAnnualQueryInf(MyAnnualQueryEntity myAnnualQueryEntity) {
                        int code = myAnnualQueryEntity.getCode();
                        switch (code){
                            case 200:
                                MyAnnualQueryEntity.DataBean data = myAnnualQueryEntity.getData();
                                tvYear.setText("本年消费情况："+new DecimalFormat("##.##").format(data.getWater_rate())+"元");
                                List<MyAnnualQueryEntity.DataBean.ConstituteOfWaterRateBean> constitute_of_water_rate = data.getConstitute_of_water_rate();
                                ArrayList<String> list = new ArrayList<>();
                                ArrayList<Integer> strList = new ArrayList<>();
                                for (int i = 0; i < constitute_of_water_rate.size(); i++) {
                                    if (constitute_of_water_rate.get(i).getPercent()>0&&constitute_of_water_rate.get(i).getPercent()<=1){
                                        list.add(constitute_of_water_rate.get(i).getKey()+":"+constitute_of_water_rate.get(i).getVar()+"元");
                                        strList.add((int) (constitute_of_water_rate.get(i).getPercent()*100));
                                    }
                                }
                                setData(list,strList);
                                List<MyAnnualQueryEntity.DataBean.HierarchicalDataBean> hierarchical_data = data.getHierarchical_data();
                                mList.clear();
                                mList.addAll(hierarchical_data);
                                adapert.notifyDataSetChanged();
                                break;
                            case 501:
                                break;
                            case 502:
                                break;
                        }
                    }
                });

                break;
            case 2018 + "":
                mPresenter = new MyAnnualQueryPresenter();
                mPresenter.setMyAnnualQuery(token, url, meterID);
                mPresenter.attarchView(new Commpart.MyAnnualQueryViewInf() {
                    @Override
                    public void getMyAnnualQueryInf(MyAnnualQueryEntity myAnnualQueryEntity) {
                        int code = myAnnualQueryEntity.getCode();
                        switch (code){
                            case 200:
                                MyAnnualQueryEntity.DataBean data = myAnnualQueryEntity.getData();
                                tvYear.setText("本年消费情况："+new DecimalFormat("##.##").format(data.getWater_rate())+"元");
                                List<MyAnnualQueryEntity.DataBean.ConstituteOfWaterRateBean> constitute_of_water_rate = data.getConstitute_of_water_rate();
                                ArrayList<String> list = new ArrayList<>();
                                ArrayList<Integer> strList = new ArrayList<>();
                                for (int i = 0; i < constitute_of_water_rate.size(); i++) {
                                    if (constitute_of_water_rate.get(i).getPercent()>0&&constitute_of_water_rate.get(i).getPercent()<=1){
                                        list.add(constitute_of_water_rate.get(i).getKey()+":"+constitute_of_water_rate.get(i).getVar()+"元");
                                        strList.add((int) (constitute_of_water_rate.get(i).getPercent()*100));
                                    }
                                }
                                setData(list,strList);
                                List<MyAnnualQueryEntity.DataBean.HierarchicalDataBean> hierarchical_data = data.getHierarchical_data();
                                mList.clear();
                                mList.addAll(hierarchical_data);
                                adapert.notifyDataSetChanged();
                                break;
                            case 501:
                                break;
                            case 502:
                                break;
                        }
                    }
                });
                break;

        }

    }

    public static AnnualChildrenFragment newInstance(String token, String meterID, String year) {
        AnnualChildrenFragment annualChildrenFragment = new AnnualChildrenFragment();
        Bundle bundle = new Bundle();
        bundle.putString("token", token);
        bundle.putString("url", year);
        bundle.putString("meterID", meterID);
        annualChildrenFragment.setArguments(bundle);
        return annualChildrenFragment;
    }

    protected void initView() {
        initPieChat();
    }

    private void initPieChat() {
        mChart.setExtraOffsets(-150f,0, ViewGroup.LayoutParams.MATCH_PARENT,0);
        Description description = new Description();
        description.setText("");
        mChart.setDescription(description);
        //设置中间圆的大小
        mChart.setHoleRadius(50f);
        //设置透明圆的大小
        mChart.setTransparentCircleRadius(55f);
        //设置中间的字体
        mChart.setCenterText("水费明细");
        //设置中间字体的大小
        mChart.setCenterTextSize(18f);
        //设置中间字体颜色
        mChart.setCenterTextColor(R.color.colorAccent);
        //设置是否总和为100%
        mChart.setUsePercentValues(true);
        //设置是否空心  true为空心 false为实心
        mChart.setDrawHoleEnabled(true);
        //设置是否可以点击  并且是否可以转动
        mChart.setTouchEnabled(true);
        //动画
        mChart.animateX(1400);
        mChart.setDrawCenterText(true);
        //调整字体位置
        Legend l = mChart.getLegend();
        //处于什么位置 TOP上  Bottom为下
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        //Right为右 Left为左
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        //纵向排列  还是横向排列
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        //X偏移量
        l.setXEntrySpace(80f);
        //字体上下间距
        l.setYEntrySpace(0f);
        //字体整体离上偏移
        l.setYOffset(10f);
    }
    private void setData(ArrayList<String> mTitleList, ArrayList<Integer> list) {
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        for (int i = 0; i < mTitleList.size() ; i++) {
            entries.add(new PieEntry(list.get(i), mTitleList.get(i)));
        }
        PieDataSet dataSet = new PieDataSet(entries, "");
        //设置空隙有多大
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        //统计图上的字体大小
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);

        mChart.highlightValues(null);

        mChart.invalidate();
    }

}
