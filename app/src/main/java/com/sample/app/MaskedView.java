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
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jipson on 4/25/14.
 */
public class MaskedView extends RelativeLayout {



        public MaskedView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

    private Bitmap mMask;
    private Bitmap mCanvasBitmap;
    private Canvas mCanvas;
    private Thread mLoader;
    private PorterDuffXfermode mXfermode;
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final SvgHelper mSvg = new SvgHelper(mPaint);
    private final Object mSvgLock = new Object();
    private List<SvgHelper.SvgPath> mPaths = new ArrayList<SvgHelper.SvgPath>(0);

    private SvgHelper.SvgPath mWaitPath;
    private SvgHelper.SvgPath mDragPath;
    private int mSvgResource;


    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            if (w != oldw || h != oldh) {
                //Create an area to use for content rendering
                mCanvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(mCanvasBitmap);
                mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
            }

            final int wi = w;
            final int hi = h;

            mSvg.load(getContext(),R.raw.bg_mask_full);
                mPaths = mSvg.getPathsForViewport(
                        wi - getPaddingLeft() - getPaddingRight(),
                        hi - getPaddingTop() - getPaddingBottom());
        }

        @Override
        protected void dispatchDraw(Canvas canvas) {

            setLayerType(View.LAYER_TYPE_SOFTWARE, null);

                int canvasCount = canvas.save();


            Path path = new Path();
            canvas.restore();


//            path.addCircle(0, 0, 700, Path.Direction.CW);
//

            final int count = mPaths.size();
//
//            for (int i = 0; i <count; i++) {
//                SvgHelper.SvgPath svgPath = mPaths.get(i);
//                canvas.drawPath(svgPath.path, svgPath.paint);
//            }

            //canvas.clipPath(mPaths.get(0).path);
            canvas.clipPath(mPaths.get(1).path);


            super.dispatchDraw(canvas);
            canvas.restoreToCount(canvasCount);


//            super.dispatchDraw(mCanvas);
//
//            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//
//            //Render the mask, clipping with a PorterDuffXfermode
//            paint.setXfermode(mXfermode);
//
//            Bitmap mask = BitmapFactory. decodeResource(getResources(), R.drawable.fdm_mask_bottom);
//            mMask = Bitmap.createScaledBitmap(mask, getWidth(), getHeight(), false);
//
//            mCanvas.drawBitmap(mMask, 0, 0, paint);
//
//            //Clear state and transfer result
//            paint.setXfermode(null);
//            canvas.drawBitmap(mCanvasBitmap, 0, 0, paint);

        }
    }

