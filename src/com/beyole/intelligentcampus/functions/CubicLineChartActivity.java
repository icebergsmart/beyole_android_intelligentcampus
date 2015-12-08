package com.beyole.intelligentcampus.functions;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.beyole.intelligentcampus.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.github.mikephil.charting.interfaces.LineDataProvider;
import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

public class CubicLineChartActivity extends DemoBase implements OnSeekBarChangeListener {

	private LineChart mChart;

	private Typeface tf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_linechart);


		mChart = (LineChart) findViewById(R.id.chart1);
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

		tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

		XAxis x = mChart.getXAxis();
		x.setEnabled(true);
		x.setTextColor(Color.WHITE);
		x.setDrawGridLines(false);
		x.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
		YAxis y = mChart.getAxisLeft();
		y.setTypeface(tf);
		y.setLabelCount(6, false);
		y.setStartAtZero(true);
		y.setTextColor(Color.WHITE);
		y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
		y.setDrawGridLines(false);
		y.setAxisLineColor(Color.WHITE);

		mChart.getAxisRight().setEnabled(false);

		// add data
		setData(24, 10000);

		mChart.getLegend().setEnabled(false);

		mChart.animateXY(2000, 2000);

		// dont forget to refresh the drawing
		mChart.invalidate();
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	private void setData(int count, float range) {

		ArrayList<String> xVals = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			xVals.add((0 + i) + "");
		}

		ArrayList<Entry> vals1 = new ArrayList<Entry>();

		for (int i = 0; i < count; i++) {
			float mult = (range + 1);
			float val = (float) (Math.random() * mult) + 20;// + (float)
																			// ((mult *
																			// 0.1) / 10);
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
		mChart.setData(data);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

	}
}
