package com.example.madroid.studydemo.materialDesign.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.madroid.studydemo.R;

import java.lang.ref.WeakReference;
import java.security.MessageDigestSpi;


/**
 * created by madroid at 2015-11-15
 */
public class MyDialog extends Dialog implements DialogInterface {
    private static final String TAG = "MyDialog";
    private final DialogController mDialog;
    private LayoutInflater mLayoutInflate;

    public MyDialog(Activity context) {
        super(context);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mDialog = new DialogController(context,this, getWindow());

//        ViewGroup viewGroup = (ViewGroup)getWindow().getDecorView() ;
//        TextView textView = new TextView(mContext) ;
//        textView.setText("this is test");
//        textView.setId(R.id.text);
//        Log.i("madroid","new textview ") ;
//        viewGroup.addView(textView);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialog.installView();

    }

    public static class Builder {

        private Activity mContext;
        private DialogController mParams;

        public Builder(Activity context) {
            mContext = context;
            mParams = new DialogController(context);
        }

        public Builder setTitle(CharSequence title) {
            mParams.setTitle(title);
            return this;
        }

        public Builder setTitle(@StringRes int title) {
            mParams.setTitle(title);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            mParams.setMessage(message);
            return this;
        }

        public Builder setMessage(@StringRes int message) {
            mParams.setMessage(message);
            return this;
        }

        public Builder setNegativeButton(CharSequence text, DialogInterface.OnClickListener listener) {
            mParams.mNegativeButtonText = text;
            mParams.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(@StringRes int text, DialogInterface.OnClickListener listener) {
            mParams.mNegativeButtonText = mContext.getText(text);
            mParams.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, DialogInterface.OnClickListener listener) {
            mParams.mPositiveButtonText = text;
            mParams.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(@StringRes int text, DialogInterface.OnClickListener listener) {
            mParams.mPositiveButtonText = mContext.getString(text);
            mParams.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            mParams.mCancelable = cancelable ;
            return this ;
        }

        /**
         * 设置Dialog的位置
         * @param gravity 默认为Gravity.CENTER，可设置Gravity.BOTTOM等等
         * @return
         */
        public Builder setGravity(int gravity) {
            mParams.mGravity = gravity ;
            return this ;
        }

        public MyDialog create() {
            MyDialog dialog = new MyDialog(mContext);
            mParams.apply(dialog.mDialog);
            dialog.setCancelable(mParams.mCancelable);
            if (mParams.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            return dialog;
        }

        public MyDialog show() {
            MyDialog dialog = create();
            dialog.show();
            return dialog;
        }

    }

    private static class DialogController {
        private Activity mContext;
        private Window mWindow;
        private LinearLayout mRootView;

        private CharSequence mTitleStr;
        private TextView mTitleView;

        private CharSequence mMessageStr;
        private TextView mMessageView;

        private CharSequence mNegativeButtonText;
        private OnClickListener mNegativeButtonListener;
        private Button mNegativeButton;
        private Message mNegativeButtonMessage;

        private CharSequence mPositiveButtonText;
        private OnClickListener mPositiveButtonListener;
        private Button mPositiveButton;
        private Message mPositiveButtonMessage;

        private boolean mCancelable ;

        private Handler mHandler;
        private DialogInterface mDialogInterface ;
        private int mGravity = Gravity.CENTER;

        private final View.OnClickListener mButtonHandler = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Message message;
                if (v == mNegativeButton && mNegativeButtonMessage != null) {
                    message = Message.obtain(mNegativeButtonMessage);
                } else if (v == mPositiveButton && mPositiveButtonMessage != null) {
                    message = Message.obtain(mPositiveButtonMessage);
                } else {
                    message = null;
                }

                if (message != null) {
                    message.sendToTarget();
                }

                mHandler.obtainMessage(ButtonHandler.MSG_DISMISS_DIALOG, mDialogInterface)
                        .sendToTarget();

            }
        };

        private static final class ButtonHandler extends Handler {

            private static final int MSG_DISMISS_DIALOG = 1;

            private WeakReference<DialogInterface> mDialog;

            public ButtonHandler(DialogInterface dialogInterface) {
                mDialog = new WeakReference<DialogInterface>(dialogInterface);
            }

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {

                    case DialogInterface.BUTTON_POSITIVE:

                    case DialogInterface.BUTTON_NEGATIVE:

                    case DialogInterface.BUTTON_NEUTRAL:
                        ((DialogInterface.OnClickListener) msg.obj).onClick(mDialog.get(), msg.what);
                        break;

                    case MSG_DISMISS_DIALOG:
                        ((DialogInterface) msg.obj).dismiss();
                }
            }
        }

        DialogController(Activity context,DialogInterface di, Window window) {

            mContext = context;
            mWindow = window;
            mDialogInterface = di ;
            ViewGroup viewGroup = (ViewGroup) mWindow.getDecorView();
            mRootView = new LinearLayout(mContext);
            mRootView.setOrientation(LinearLayout.VERTICAL);
            viewGroup.addView(mRootView);
            mHandler = new ButtonHandler(di) ;

        }

        DialogController(Activity context) {
            mContext = context;
        }

        public void installView() {
            Log.i(TAG, "installView");

            setupView();
        }

        private boolean setupTitle(ViewGroup topPanel) {
            Log.i(TAG, "setupTitle");
            boolean hasTitle = !TextUtils.isEmpty(mTitleStr);

            if (hasTitle) {
                mTitleView = (TextView) topPanel.findViewById(R.id.title);
                if (mTitleView == null) {
                    return false;
                }
                mTitleView.setText(mTitleStr);
            } else {
                topPanel.setVisibility(View.GONE);
                hasTitle = false;
            }
            Log.i(TAG, "has title : " + hasTitle);
            return hasTitle;
        }

        private boolean setupContent(ViewGroup content) {
            Log.i(TAG, "setupContent");
            boolean hasContent = true;
            mMessageView = (TextView) content.findViewById(R.id.message);

            if (mMessageStr != null) {
                mMessageView.setText(mMessageStr);
            } else {
                content.setVisibility(View.GONE);
                hasContent = false;
            }

            return hasContent;
        }

        private boolean setupButtons(ViewGroup footer) {
            Log.i(TAG, "setupButtons");
            int BIT_BUTTON_POSITIVE = 1;
            int BIT_BUTTON_NEGATIVE = 2;
            int BIT_BUTTON_NEUTRAL = 4;
            int whichButtons = 0;

            mPositiveButton = (Button) footer.findViewById(R.id.button_positive);
            mPositiveButton.setOnClickListener(mButtonHandler);
            if (TextUtils.isEmpty(mPositiveButtonText)) {
                mPositiveButton.setVisibility(View.GONE);
            } else {
                mPositiveButton.setText(mPositiveButtonText);
                mPositiveButton.setVisibility(View.VISIBLE);
                whichButtons = BIT_BUTTON_POSITIVE | whichButtons;
            }

            mNegativeButton = (Button) footer.findViewById(R.id.button_negative);
            mNegativeButton.setOnClickListener(mButtonHandler);
            if (TextUtils.isEmpty(mNegativeButtonText)) {
                mNegativeButton.setVisibility(View.GONE);
            } else {
                mNegativeButton.setText(mNegativeButtonText);
                mNegativeButton.setVisibility(View.VISIBLE);
                whichButtons = whichButtons | BIT_BUTTON_NEGATIVE;
            }

            return whichButtons != 0;
        }

        private void setupView() {
            Log.i(TAG, "setupView");

            ViewGroup titlePanel = (ViewGroup) inflateView(R.layout.dialog_title);
            boolean showTitle = setupTitle(titlePanel);
            mRootView.addView(titlePanel);

            ViewGroup contentPanel = (ViewGroup) inflateView(R.layout.dialog_content_msg);
            boolean showContent = setupContent(contentPanel);
            mRootView.addView(contentPanel);

            ViewGroup buttons = (ViewGroup) inflateView(R.layout.dialog_footer);
            boolean showButton = setupButtons(buttons);
            mRootView.addView(buttons);

            if (!showTitle && !showContent && !showButton) {
                mRootView.setVisibility(View.GONE);
            }
        }

        private View inflateView(@LayoutRes int layout) {
            return mContext.getLayoutInflater().inflate(layout, null);
        }

        private void setTitle(CharSequence title) {
            mTitleStr = title;
            if (mTitleView != null) {
                mTitleView.setText(mTitleStr);
            }
        }

        private void setTitle(@StringRes int title) {
            setTitle(mContext.getString(title));
        }

        private CharSequence getTitle() {
            CharSequence title = "";
            if (mTitleView != null) {
                title = mTitleView.getText();
            }
            return title;
        }

        private void setMessage(CharSequence message) {
            mMessageStr = message;
            if (mMessageView != null) {
                mMessageView.setText(mMessageStr);
            }
        }

        private void setMessage(@StringRes int message) {
            setMessage(mContext.getString(message));
        }

        private CharSequence getMessage() {
            CharSequence message = "";
            if (mMessageView != null) {
                message = mMessageView.getText();
            }
            return message;
        }

        private void setGravity(int gravity) {
            if (mWindow != null) {
                mWindow.setGravity(mGravity);
            }
        }

        private void setButton(int which, CharSequence text,
                               DialogInterface.OnClickListener listener, Message msg) {

            if (msg == null && listener != null) {
                msg = mHandler.obtainMessage(which, listener) ;
            }

            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    mPositiveButtonText = text;
                    mPositiveButtonMessage = msg ;
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    mNegativeButtonText = text;
                    mNegativeButtonMessage = msg ;
                    break;

                case DialogInterface.BUTTON_NEUTRAL:

                    break;

                default:
                    throw new IllegalArgumentException("Button does not exist");
            }

        }

        public void apply(DialogController dialog) {

            dialog.setGravity(mGravity);
            if (mTitleStr != null) {
                dialog.setTitle(mTitleStr);
            }

            if (mMessageStr != null) {
                dialog.setMessage(mMessageStr);
            }

            if (mPositiveButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_POSITIVE,
                        mPositiveButtonText, mPositiveButtonListener, null);
            }

            if (mNegativeButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                        mNegativeButtonText, mNegativeButtonListener, null);
            }
        }
    }
}
