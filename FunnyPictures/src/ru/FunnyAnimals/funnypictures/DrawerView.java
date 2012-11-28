package ru.FunnyAnimals.funnypictures;

import java.util.ArrayList;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.Scroller;

public class DrawerView extends ImageView {
	private Context ctx;
	private Path path;
	private ArrayList<Path> graphics;
	private Paint mPaint;
	
	private float previousX;
	private float previousY;
	private float mScaleFactor = 1.f;
	private ScaleGestureDetector mScaleDetector;
	

    private float mPosX;
    private float mPosY;
    private float scalePointX;
    private float scalePointY;



	
	public DrawerView(Context context) {
		super(context);
		setDefaultParams(context);
	}
	
	public DrawerView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		setDefaultParams(context);
	}
	
	public DrawerView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		setDefaultParams(context);
	}
	
	private void setDefaultParams(Context context) {

		this.ctx = context;
		graphics = new ArrayList<Path>();
		mPaint = new Paint();
		mPaint.setColor(Color.BLUE);
		mPaint.setDither(true);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(3);
		this.setBackgroundColor(Color.WHITE);
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
	}
	
	public void stepBack()
	{
		if (graphics.size() > 0)
		{
		graphics.remove(graphics.size()-1);
		this.invalidate();
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mScaleDetector.onTouchEvent(event);
		if(event.getPointerCount() > 1) {
			path = null;
		}
		else {
			drawLines(event);
		}
		invalidate();

		return true;
	}
	
	private void drawLines(MotionEvent event)
	{
		float thisX = event.getX() / mScaleFactor;
		float thisY = event.getY() / mScaleFactor;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path = new Path();
			path.moveTo(thisX, thisY);
			path.lineTo(thisX + 1, thisY + 1);
			previousX = thisX;
			previousY = thisY;
			break;				
		case MotionEvent.ACTION_MOVE:
			 if (!mScaleDetector.isInProgress()) {
	                final float dx = thisX - previousX; // change in X
	                final float dy = thisY - previousY; // change in Y

	                mPosX += dx;
	                mPosY += dy;

	                invalidate();
	            }

			if (path != null) {
				path.moveTo(previousX, previousY);
				path.lineTo(thisX, thisY);
			}
			previousX = thisX;
			previousY = thisY;
			break;
		case MotionEvent.ACTION_UP:
			if (path != null) {
				graphics.add(path);
				path = null;	
			}
			break;	
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);	
		canvas.save();
	    canvas.scale(mScaleFactor, mScaleFactor, scalePointX, scalePointY);

		 if (path!= null)
			 canvas.drawPath(path, mPaint);
		 for (Path tempPath : graphics) {
			    canvas.drawPath(tempPath, mPaint);
			  }
	     canvas.translate(mPosX, mPosY);
		 canvas.restore();	
	}
	


private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

    private float lastFocusX = -1;
    private float lastFocusY = -1;

	@Override
	public boolean onScaleBegin(ScaleGestureDetector detector) {
		lastFocusX = -1;
        lastFocusY = -1;
        return true;

	}
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
    	 mScaleFactor *= detector.getScaleFactor();
         scalePointX =  detector.getFocusX();
         scalePointY = detector.getFocusY();

         // Don't let the object get too small or too large.
         mScaleFactor = Math.max(1f, Math.min(mScaleFactor, 5.0f));

         invalidate();
        return true;
    }
}


}