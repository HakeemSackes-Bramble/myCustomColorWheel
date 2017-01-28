package nyc.c4q.hakeemsackes_bramble.customcolorview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hakeemsackes-bramble on 1/27/17.
 */

public class ColorWheel extends View {

    final float radius;
    float xValue;
    float yValue;
    /* my transparency value*/
    float distFromCenter;
    final float centerPointX;
    final float centerPointY;
    Paint paint;
    Color color;



    public ColorWheel(final Context context, AttributeSet attrs) {
        super(context, attrs);

        centerPointX = 400;
        centerPointY = 400;
        radius = 400;
        color= new Color();
        paint =new Paint(Paint.ANTI_ALIAS_FLAG);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                xValue = event.getX();
                yValue = event.getY();
                distFromCenter = distanceFromPoint(centerPointX, centerPointY);

                if (distFromCenter < radius) {
                    float transperancy = distFromCenter / radius * 255;
                    float redValue = distanceFromPoint(centerPointX, centerPointY - radius) / radius * 255;
                    float greenValue = (distanceFromPoint(centerPointX + (radius * Math.sqrt(3) / 2), centerPointY + (radius / 2)) / (radius * 2)) * 255;
                    float blueValue = (distanceFromPoint(centerPointX - (radius * Math.sqrt(3) / 2), centerPointY + (radius / 2)) / (radius * 2)) * 255;
                    int colorValue = color.argb((int) transperancy,(int)redValue,(int) greenValue,(int)blueValue);
                    paint.setColor(colorValue);
                }
                return false;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerPointX, centerPointY, radius, paint);
        invalidate();
    }


    public float distanceFromPoint(double x, double y) {
        return (float) Math.sqrt(Math.pow(x - xValue, 2) + Math.pow(y - yValue, 2));
    }

}
