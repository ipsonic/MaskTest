package com.sample.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by jipson on 4/25/14.
 */
public class MaskedView extends RelativeLayout {

    private Bitmap mMask;

        public MaskedView(Context context, AttributeSet attrs) {
            super(context, attrs);
            //mMask = BitmapFactory.decodeResource(getResources(), R.drawable.fdm_mask_bottom);

        }

        @Override
        protected void dispatchDraw(Canvas canvas) {

            setLayerType(View.LAYER_TYPE_SOFTWARE, null);

//            Bitmap resizedBitmap = Bitmap.createScaledBitmap(mMask, this.getWidth(), this.getHeight(), false);
//            Bitmap result = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888); //create another bitmap with same height
//
//            Canvas mCanvas = new Canvas(result);
//            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//
//
//            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//            mCanvas.drawBitmap(resizedBitmap, 0, 0, paint);
//            paint.setXfermode(null);


            Path path = new Path();
            int count = canvas.save();

            path.addCircle(400, 200, 300, Path.Direction.CW);

            canvas.clipPath(path);

            super.dispatchDraw(canvas);
            canvas.restoreToCount(count);

        }
    }

