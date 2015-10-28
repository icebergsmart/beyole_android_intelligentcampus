package com.beyole.intelligentcampus.functions;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beyole.intelligentcampus.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.github.mikephil.charting.interfaces.LineDataProvider;

public class SportTodayFragment extends Fragment {
	private LineChart mChart,mChart1;

	private Typeface tf;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_sporttoday, null);
		mChart = (LineChart) view.findViewById(R.id.chart1);
		mChart1 = (LineChart) view.findViewById(R.id.chart2);
		
		mChart.setViewPortOffsets(0, 20, 0, 0);
		mChart.setBackgroundColor(Color.rgb(104, 241, 175));
		// no description text
		mChart.setDescription("");
		// enable touch gestures
		mChart.setTouchEnabled(true);
		// enable scaling and dragging
		mChart.setDragEnabled(false);
		mChart.setScaleEnabled(false);
		// if disabled, scaling can be done on x- and y-axis separately
		mChart.setPinchZoom(false);
		mChart.setDrawGridBackground(false);
		tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
		XAxis x = mChart.getXAxis();
		x.setEnabled(true);
		x.setTextColor(Color.WHITE);
		x.setDrawGridLines(false);
		x.setAxisLineColor(Color.WHITE);
		x.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
		YAxis y = mChart.getAxisLeft();
		y.setEnabled(true);
		y.setDrawGridLines(false);
		y.setAxisLineColor(Color.WHITE);
		mChart.getAxisRight().setEnabled(false);
		// add data
		setData(24, 10000,mChart);
		mChart.getLegend().setEnabled(false);
		mChart.animateXY(2000, 2000);
		// dont forget to refresh the drawing
		mChart.invalidate();
		
		
		//爬楼层数据设置
		
		mChart1.setViewPortOffsets(0, 20, 0, 0);
		mChart1.setBackgroundColor(Color.rgb(104, 241, 175));
		// no description text
		mChart1.setDescription("");
		// enable touch gestures
		mChart1.setTouchEnabled(true);
		// enable scaling and dragging
		mChart1.setDragEnabled(false);
		mChart1.setScaleEnabled(false);
		// if disabled, scaling can be done on x- and y-axis separately
		mChart1.setPinchZoom(false);
		mChart1.setDrawGridBackground(false);
		XAxis x1 = mChart1.getXAxis();
		x1.setEnabled(true);
		x1.setTextColor(Color.WHITE);
		x1.setDrawGridLines(false);
		x1.setAxisLineColor(Color.WHITE);
		x1.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
		YAxis y1 = mChart1.getAxisLeft();
		y1.setEnabled(true);
		y1.setDrawGridLines(false);
		y1.setAxisLineColor(Color.WHITE);
		mChart1.getAxisRight().setEnabled(false);
		// add data
		setData(24, 10000,mChart1);
		mChart1.getLegend().setEnabled(false);
		mChart1.animateXY(2000, 2000);
		// dont forget to refresh the drawing
		mChart1.invalidate();
		
		return view;
	}

	private void setData(int count, float range,LineChart chart) {
		ArrayList<String> xVals = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			xVals.add((0 + i) + "");
		}
		ArrayList<Entry> vals1 = new ArrayList<Entry>();
		for (int i = 0; i < count; i++) {
			float mult = (range + 1);
			float val = (float) (Math.random() * mult) + 20;// + (float)
			vals1.add(new Entry(val, i));
		}
		// create a dataset and give it a type
		LineDataSet set1 = new LineDataSet(vals1, "DataSet 1");
		set1.setDrawCubic(true);
		set1.setCubicIntensity(0.2f);
		// set1.setDrawFilled(true);
		set1.setDrawCircles(false);
		set1.setLineWidth(1.8f);
		set1.setCircleSize(4f);
		set1.setCircleColor(Color.WHITE);
		set1.setHighLightColor(Color.rgb(244, 117, 117));
		set1.setColor(Color.WHITE);
		set1.setFillColor(Color.WHITE);
		set1.setFillAlpha(100);
		set1.setDrawHorizontalHighlightIndicator(false);
		set1.setFillFormatter(new FillFormatter() {
			@Override
			public float getFillLinePosition(LineDataSet dataSet, LineDataProvider dataProvider) {
				return -10;
			}
		});
		// create a data object with the datasets
		LineData data = new LineData(xVals, set1);
		data.setValueTypeface(tf);
		data.setValueTextSize(9f);
		data.setDrawValues(false);
		// set data
		chart.setData(data);
	}

}
