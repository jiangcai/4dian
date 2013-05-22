package cn.com.jdsc;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;



import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeActivity extends Activity{
	public static final String TYPE = "type";

	private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();

	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

	private XYSeries mCurrentSeries;

	private XYSeriesRenderer mCurrentRenderer;

	private String mDateFormat;

	private GraphicalView mChartView;

	/**
	 * 提取保存数据 恢复Activity状态
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedState) {
		super.onRestoreInstanceState(savedState);
		mDataset = (XYMultipleSeriesDataset) savedState
				.getSerializable("dataset");
		mRenderer = (XYMultipleSeriesRenderer) savedState
				.getSerializable("renderer");
		mCurrentSeries = (XYSeries) savedState
				.getSerializable("current_series");
		mCurrentRenderer = (XYSeriesRenderer) savedState
				.getSerializable("current_renderer");
		mDateFormat = savedState.getString("date_format");
	}

	/**
	 * 在Activity销毁前 保存数据状态
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable("dataset", mDataset);
		outState.putSerializable("renderer", mRenderer);
		outState.putSerializable("current_series", mCurrentSeries);
		outState.putSerializable("current_renderer", mCurrentRenderer);
		outState.putString("date_format", mDateFormat);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_home);

		TextView signal = (TextView) findViewById(R.id.title_Text);
		signal.setText("时间管理");
		
		// 创建 系列（画笔、渲染）
		String seriesTitle = "本月记录";
		XYSeries series = new XYSeries(seriesTitle);
		mDataset.addSeries(series);
		mCurrentSeries = series;
		XYSeriesRenderer seriesRenderer = new XYSeriesRenderer();

		/*
		 * 可以多个折线
		 */
		// 设置seriesRenderer风格
		int pen_color= this.getResources().getColor(R.color.title);
		seriesRenderer.setColor(pen_color); // 画笔颜色
		//seriesRenderer.setFillBelowLine(true); // 确定填充
		//seriesRenderer.setFillBelowLineColor(Color.RED); // 填充颜色
		seriesRenderer.setPointStyle(PointStyle.CIRCLE); // 设置画笔风格
		//seriesRenderer.setLineWidth(3.0f); // 设置画笔宽度

		mRenderer.addSeriesRenderer(seriesRenderer);//这条折线添加到图表中
		
		//设置图表风格

		 mRenderer.setShowGrid(true);
		 mRenderer.setShowCustomTextGrid(true);
		 mRenderer.setXLabelsAlign(Align.RIGHT);
		 mRenderer.setYLabelsAlign(Align.RIGHT);
		 
		int color = this.getResources().getColor(R.color.mibai);  //外围背景色

		mRenderer.setBackgroundColor(Color.WHITE);//设置图表内部颜色
		mRenderer.setMarginsColor(color);//设置周围的黑框颜色
			    
		mRenderer.setYAxisMin(0); // 设置Y维度最小值
		mRenderer.setXAxisMin(0); // 设置X维度最小值
		mRenderer.setYAxisMax(12); // 设置Y维度最大值
		mRenderer.setXAxisMax(30);// 设置X维度最大值
		mRenderer.setShowGrid(true); // 设置背景格子
		mRenderer.setXLabels(30); // 设置X坐标分成31份
		mRenderer.setYLabels(12);  
		mRenderer.setChartTitle("起床记录");//圖表的title
		
		mRenderer.setPanLimits(new double[] { 0, 31, 0, 23 });//设置x，y轴变化范围的,但是有bug，反复上下拖拽，y的变化范围会增加
		mRenderer.setZoomLimits(new double[] { 0, 31, 0, 23 });//缩放范围
		mRenderer.setZoomButtonsVisible(true);

		
		
		int length = mRenderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) mRenderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
		mCurrentRenderer = seriesRenderer;

		// 数据绘制 To Do
		double x = 0;
		double y = 0;
		// 获取 (x,y) 数据集
		double[][] xyValues = getValue();

		for (int i = 0; i < xyValues.length; i++) {
			x = xyValues[i][0];
			y = xyValues[i][1];
		mCurrentSeries.add(x, y);
	}

	}

	/**
	 * 在 onResume 里进行绘制 在横屏切换时自动调用
	 */
	@Override
	protected void onResume() {
		super.onResume();
		if (mChartView == null) {
			LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
			mChartView = ChartFactory.getLineChartView(this, mDataset,
					mRenderer);
			layout.addView(mChartView);

		} else {
		//	mChartView.setBackgroundResource(R.id.chart);    //这是干什么的？
			mChartView.repaint();
		}

	}

	/**
	 * 数据入口
	 * 
	 * @return x、y 坐标集
	 */
	
	private double[][] getValue(){
		double[][] values={
				{0,1.2},{1,2.2},{2,4.5},{3,5.6},{4,4.5},{3,5.5},{5,2.3},{6,10.2},{7,11.3},
				{8,11.3},{17,9.2},{20,4.5},{10,1.2},{11,2.2},{14,4.5},{13,5.5},{15,2.3},{16,10.2},{17,10.3},
				{18,11.3},{12,4.5},{13,5.6}
		};
		return values;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return super.onTouchEvent(event);
	}
}