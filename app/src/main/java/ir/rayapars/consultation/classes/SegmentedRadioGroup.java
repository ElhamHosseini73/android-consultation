package ir.rayapars.consultation.Classes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

import ir.rayapars.consultation.R;


public class SegmentedRadioGroup extends RadioGroup {
    public SegmentedRadioGroup(Context context) {
        super(context);
    }

    public SegmentedRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void a() {
        int i;
        View childAt;
        int childCount = super.getChildCount();
        if (childCount > 1) {
            int i2;
            super.getChildAt(0).setBackgroundResource(R.drawable.segment_radio_left);
            i = 1;
            while (true) {
                i2 = childCount - 1;
                if (i >= i2) {
                    break;
                }
                super.getChildAt(i).setBackgroundResource(R.drawable.segment_radio_middle);
                i++;
            }
            childAt = super.getChildAt(i2);
            i = R.drawable.segment_radio_right;
        } else if (childCount == 1) {
            childAt = super.getChildAt(0);
            i = R.drawable.segment_button;
        } else {
            return;
        }
        childAt.setBackgroundResource(i);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        a();
    }
}
