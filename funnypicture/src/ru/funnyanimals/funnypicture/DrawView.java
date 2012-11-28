package ru.funnyanimals.funnypicture;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class DrawView extends ImageView {

	private ArrayList<Path> graphics;
	private Paint mPaint;
	
	private Context ctx;
	private boolean isDraw;
	private float X;
	private float Y;
	private float previousX;
	private float previousY;
	
	public DrawView(Context context) {
		super(context);
		ctx = context;
		graphics = new ArrayList<Path>();
		mPaint = new Paint();
		mPaint.setColor(Color.BLUE);
		mPaint.setDither(true);
		  mPaint.setStyle(Paint.Style.STROKE);
		  mPaint.setStrokeJoin(Paint.Join.ROUND);
		  mPaint.setStrokeCap(Paint.Cap.ROUND);
		  mPaint.setStrokeWidth(3);
	}
	
	public DrawView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		ctx = context;
	}
	
	public DrawView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		ctx = context;
	}
	
	public void stepBack()
	{
		graphics.remove(graphics.size()-1);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Path path = new Path();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(event.getX(), event.getY());
			path.lineTo(event.getX()+1, event.getY()+1);
			previousX = event.getX();
			previousY = event.getY();
			break;				
		case MotionEvent.ACTION_MOVE:
			path.moveTo(previousX, previousY);
			path.lineTo(event.getX(), event.getY());	
			previousX = event.getX();
			previousY = event.getY();
			break;
		case MotionEvent.ACTION_UP:
			path.moveTo(previousX, previousY);
			path.lineTo(event.getX(), event.getY());	
			break;	
		}
		graphics.add(path);
		invalidate();

		return true;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		 for (Path path : graphics) {
			    //canvas.drawPoint(graphic.x, graphic.y, mPaint);
			    canvas.drawPath(path, mPaint);
			  }
		super.onDraw(canvas);		
	}

	
}
