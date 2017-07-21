package com.winwin.app.UI.OtherView;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.winwin.app.Constant.Data;
import com.winwin.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IndexFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndexFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.WebView)
    BridgeWebView mWebView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private static final String TAG = IndexFragment.class.getSimpleName();
    private Toolbar toolbar;
    private static final String URL = "http://winwin.jidichong.com/#/workbench?token="+ Data.getUserToken();

    public IndexFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IndexFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IndexFragment newInstance(String param1, String param2) {
        IndexFragment fragment = new IndexFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        ButterKnife.bind(this, view);

        initWebView();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * 初始化WebView配置
     */
    private void initWebView() {
        WebSettings settings = mWebView.getSettings();

        // BindUserInfo settings
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        settings.setJavaScriptEnabled(true);    //设置webview支持javascript
        settings.setLoadsImagesAutomatically(true);    //支持自动加载图片
        settings.setUseWideViewPort(true);    //设置webview推荐使用的窗口，使html界面自适应屏幕
        settings.setLoadWithOverviewMode(true); //和setUseWideViewPort(true)一起解决网页自适应问题
        settings.setSaveFormData(true);    //设置webview保存表单数据
        settings.setSavePassword(true);    //设置webview保存密码
        settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);    //设置中等像素密度，medium=160dpi
        settings.setSupportZoom(true);    //支持缩放

        // Technical settings
        settings.setAppCacheEnabled(false);  //是否使用缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);    //DOM Storage
        settings.setSupportMultipleWindows(true);
        mWebView.setLongClickable(true);
        mWebView.setScrollbarFadingEnabled(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setDrawingCacheEnabled(true);

        CookieManager.getInstance().setAcceptCookie(true);

        if (Build.VERSION.SDK_INT > 8) {
            settings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        }

        Log.e(TAG, "URL "+URL);
        mWebView.loadUrl(URL);
        mWebView.addJavascriptInterface(new IndexFragment.JSHook(), "SetAndroidJavaScriptBridge");
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(android.webkit.WebView view, String title) {
                Log.d(TAG, "－－－－－－setWebChromeClient ");
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
                //返回值是true的时候WebView打开，为false则系统浏览器或第三方浏览器打开。
                //如果要下载页面中的游戏或者继续点击网页中的链接进入下一个网页的话，重写此方法下，不然就会跳到手机自带的浏览器了，而不继续在你这个webview里面展现了
//                return super.shouldOverrideUrlLoading(view, url);
                Log.d(TAG, " url:" + url);
                view.loadUrl(url);// 当打开新链接时，使用当前的 WebView，不会使用系统其他浏览器
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //想在页面开始加载时有操作，在这添加
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //想在页面加载结束时有操作，在这添加
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                //想在收到错误信息的时候，执行一些操作，走此方法
                super.onReceivedError(view, request, error);
            }
        });
    }

    /**
     * 设置回退
     *
     * @param keyCode
     * @param event
     * @return
     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        //退出web还是退出activity
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
//            mWebView.goBack(); //goBack()表示返回WebView的上一页面
//            return true;
//        } else {
//            this.finish();
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    public String type;

    public class JSHook {

        @JavascriptInterface
        public void pay(String info) {  //点击"支付订单"按钮时候调用
            Log.e(TAG, "pay");
        }

        public String getInfo() {
            return "获取手机内的信息！！";
        }
    }
}
