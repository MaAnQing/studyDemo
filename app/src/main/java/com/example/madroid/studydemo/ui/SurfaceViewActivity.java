package com.example.madroid.studydemo.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.madroid.studydemo.R;

public class SurfaceViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
    }


//    //视图内部类
//    class MyView extends SurfaceView implements SurfaceHolder.Callback
//    {
//        private SurfaceHolder holder;
//        private MyThread myThread;
//        public MyView(Context context) {
//            super(context);
//            // TODO Auto-generated constructor stub
//            holder = this.getHolder();
//            holder.addCallback(this);
//            myThread = new MyThread(holder);//创建一个绘图线程
//        }
//
//        @Override
//        public void surfaceChanged(SurfaceHolder holder, int format, int width,
//                                   int height) {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public void surfaceCreated(SurfaceHolder holder) {
//            // TODO Auto-generated method stub
//            myThread.isRun = true;
//            myThread.start();
//        }
//
//        @Override
//        public void surfaceDestroyed(SurfaceHolder holder) {
//            // TODO Auto-generated method stub
//            myThread.isRun = false;
//        }
//
//    }
//    //线程内部类
//    class MyThread extends Thread
//    {
//        private SurfaceHolder holder;
//        public boolean isRun ;
//        public  MyThread(SurfaceHolder holder)
//        {
//            this.holder =holder;
//            isRun = true;
//        }
//        @Override
//        public void run()
//        {
//            int count = 0;
//            int radius = 40 ;
//            while(isRun)
//            {
//                Canvas c = null;
//                try
//                {
//                    synchronized (holder)
//                    {
//                        c = holder.lockCanvas();//锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
//                        c.drawColor(Color.BLACK);//设置画布背景颜色
//                        Paint p = new Paint(); //创建画笔
//                        p.setStyle(Paint.Style.STROKE);
//                        p.setColor(Color.WHITE);
////                        Rect r = new Rect(100 + 10*count, 50, 300+ 10*count, 250);
////                        c.drawRect(r, p);
//                        c.drawCircle(540f,900f,radius,p);
//                        c.drawCircle(540f,900f,radius * 1.35F,p);
//                        c.drawCircle(540f,900f,radius * 1.35F*1.35F,p);
//                        c.drawCircle(540f,900f,radius * 1.35F*1.35F*1.35F,p);
//                        c.drawCircle(540f,900f,radius * 1.35F*1.35F*1.35F*1.35F,p);
//
//                        c.drawText("这是第" + (count) + "次", 100, 310, p);
//                        radius = radius + count++ ;
//                        Thread.sleep(100);//睡眠时间为1秒
//                    }
//                }
//                catch (Exception e) {
//                    // TODO: handle exception
//                    e.printStackTrace();
//                }
//                finally
//                {
//                    if(c!= null)
//                    {
//                        holder.unlockCanvasAndPost(c);//结束锁定画图，并提交改变。
//
//                    }
//                }
//            }
//        }
//    }





}
