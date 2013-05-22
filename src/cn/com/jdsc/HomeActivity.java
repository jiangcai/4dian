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
	 * ��ȡ�������� �ָ�Activity״̬
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
	 * ��Activity����ǰ ��������״̬
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
		signal.setText("ʱ�����");
		
		// ���� ϵ�У����ʡ���Ⱦ��
		String seriesTitle = "���¼�¼";
		XYSeries series = new XYSeries(seriesTitle);
		mDataset.addSeries(series);
		mCurrentSeries = series;
		XYSeriesRenderer seriesRenderer = new XYSeriesRenderer();

		/*
		 * ���Զ������
		 */
		// ����seriesRenderer���
		int pen_color= this.getResources().getColor(R.color.title);
		seriesRenderer.setColor(pen_color); // ������ɫ
		//seriesRenderer.setFillBelowLine(true); // ȷ�����
		//seriesRenderer.setFillBelowLineColor(Color.RED); // �����ɫ
		seriesRenderer.setPointStyle(PointStyle.CIRCLE); // ���û��ʷ��
		//seriesRenderer.setLineWidth(3.0f); // ���û��ʿ��

		mRenderer.addSeriesRenderer(seriesRenderer);//����������ӵ�ͼ����
		
		//����ͼ����

		 mRenderer.setShowGrid(true);
		 mRenderer.setShowCustomTextGrid(true);
		 mRenderer.setXLabelsAlign(Align.RIGHT);
		 mRenderer.setYLabelsAlign(Align.RIGHT);
		 
		int color = this.getResources().getColor(R.color.mibai);  //��Χ����ɫ

		mRenderer.setBackgroundColor(Color.WHITE);//����ͼ���ڲ���ɫ
		mRenderer.setMarginsColor(color);//������Χ�ĺڿ���ɫ
			    
		mRenderer.setYAxisMin(0); // ����Yά����Сֵ
		mRenderer.setXAxisMin(0); // ����Xά����Сֵ
		mRenderer.setYAxisMax(12); // ����Yά�����ֵ
		mRenderer.setXAxisMax(30);// ����Xά�����ֵ
		mRenderer.setShowGrid(true); // ���ñ�������
		mRenderer.setXLabels(30); // ����X����ֳ�31��
		mRenderer.setYLabels(12);  
		mRenderer.setChartTitle("�𴲼�¼");//�D���title
		
		mRenderer.setPanLimits(new double[] { 0, 31, 0, 23 });//����x��y��仯��Χ��,������bug������������ק��y�ı仯��Χ������
		mRenderer.setZoomLimits(new double[] { 0, 31, 0, 23 });//���ŷ�Χ
		mRenderer.setZoomButtonsVisible(true);

		
		
		int length = mRenderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) mRenderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
		mCurrentRenderer = seriesRenderer;

		// ���ݻ��� To Do
		double x = 0;
		double y = 0;
		// ��ȡ (x,y) ���ݼ�
		double[][] xyValues = getValue();

		for (int i = 0; i < xyValues.length; i++) {
			x = xyValues[i][0];
			y = xyValues[i][1];
		mCurrentSeries.add(x, y);
	}

	}

	/**
	 * �� onResume ����л��� �ں����л�ʱ�Զ�����
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
		//	mChartView.setBackgroundResource(R.id.chart);    //���Ǹ�ʲô�ģ�
			mChartView.repaint();
		}

	}

	/**
	 * �������
	 * 
	 * @return x��y ���꼯
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