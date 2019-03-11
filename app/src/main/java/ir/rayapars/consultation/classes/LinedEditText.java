package ir.rayapars.consultation.classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class LinedEditText extends AppCompatEditText {

    private Rect a = new Rect();
    private Paint b = new Paint();

    public LinedEditText(Context context) {

        super(context);

    }

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.b.setStyle(Paint.Style.STROKE);
        this.b.setColor(2006357654);
    }

    public LinedEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onDraw(Canvas canvas) {
        int height = canvas.getHeight();
        Rect rect = this.a;
        Paint paint = this.b;
        int lineBounds = getLineBounds(0, rect) + 1;
        while (lineBounds < height) {
            float f = (float) lineBounds;
            canvas.drawLine((float) rect.left, f, (float) rect.right, f, paint);
            lineBounds += getLineHeight();
        }
        super.onDraw(canvas);
    }


}
