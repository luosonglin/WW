package com.winwin.app.view;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.winwin.app.R;

public class SignUpActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static ViewPager mViewPager;

    // UI references.
    private ImageView mBackgroundImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // init toolbar
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBackgroundImageView = (ImageView) findViewById(R.id.login_background_image);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation= AnimationUtils.loadAnimation(SignUpActivity.this,R.anim.login_background_translate_anim);
                mBackgroundImageView.startAnimation(animation);
            }
        },1000);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_sign_up2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = SignUpPlaceholderFirstFragment.newInstance(position);
                    break;
                case 1:
                    fragment = SignUpPlaceholderSecondFragment.newInstance(position);
                    break;
                case 2:
                    fragment = SignUpPlaceholderThirdFragment.newInstance(position);
                    break;
                default:
                    fragment = SignUpPlaceholderFirstFragment.newInstance(position);
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SignUpPlaceholderFirstFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        // UI Refrence
        private TextView mPhoneView;
        private EditText mCodeView;
        private TextView mGetCodeView;
        private Button mConfirmView;

        // timer
        CountDownTimer timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                mGetCodeView.setEnabled(false );
                mGetCodeView.setText("剩余" + l / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                mGetCodeView.setEnabled(true);
                mGetCodeView.setText("获取验证码");
            }
        };

        public SignUpPlaceholderFirstFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SignUpPlaceholderFirstFragment newInstance(int sectionNumber) {
            SignUpPlaceholderFirstFragment fragment = new SignUpPlaceholderFirstFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sign_up_first, container, false);
            mPhoneView = (TextView) rootView.findViewById(R.id.phone);
            mCodeView = (EditText) rootView.findViewById(R.id.code);
            mGetCodeView = (TextView) rootView.findViewById(R.id.get_code_textview);
            mGetCodeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    timer.start();
                }
            });
            mConfirmView = (Button) rootView.findViewById(R.id.confirm_button);
            mConfirmView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewPager.setCurrentItem(getArguments().getInt(ARG_SECTION_NUMBER) + 1, true);
                }
            });
            return rootView;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SignUpPlaceholderSecondFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        // UI Refrence
        private TextView mPhoneView;
        private EditText mCodeView;
        private TextView mGetCodeView;
        private Button mConfirmView;

        public SignUpPlaceholderSecondFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SignUpPlaceholderSecondFragment newInstance(int sectionNumber) {
            SignUpPlaceholderSecondFragment fragment = new SignUpPlaceholderSecondFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sign_up_second, container, false);
            mPhoneView = (TextView) rootView.findViewById(R.id.phone);
            mCodeView = (EditText) rootView.findViewById(R.id.code);
            mConfirmView = (Button) rootView.findViewById(R.id.confirm_button);
            mConfirmView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewPager.setCurrentItem(getArguments().getInt(ARG_SECTION_NUMBER) + 1, true);
                }
            });
            return rootView;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SignUpPlaceholderThirdFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        // UI Refrence
        private TextView mPhoneView;
        private EditText mCodeView;
        private TextView mGetCodeView;
        private Button mConfirmView;

        public SignUpPlaceholderThirdFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SignUpPlaceholderThirdFragment newInstance(int sectionNumber) {
            SignUpPlaceholderThirdFragment fragment = new SignUpPlaceholderThirdFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sign_up_third, container, false);
            mPhoneView = (TextView) rootView.findViewById(R.id.phone);
            mCodeView = (EditText) rootView.findViewById(R.id.code);
            mConfirmView = (Button) rootView.findViewById(R.id.confirm_button);
            mConfirmView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewPager.setCurrentItem(getArguments().getInt(ARG_SECTION_NUMBER) + 1, true);
                }
            });
            return rootView;
        }
    }
}
