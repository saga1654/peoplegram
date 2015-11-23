package kr.co.people_gram.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class Guide_Activity extends FragmentActivity {

    private final int COUNT=4;
    private ViewPager mPager;	//뷰 페이저

    private LinearLayout mPageMark;			//현재 몇 페이지 인지 나타내는 뷰
    private int mPrevPosition;						//이전에 선택되었던 포지션 값

    private Button mPrev, mNext;					//이전버튼, 다음버튼
    private RadioGroup mOption;				//아이템 이동시 애니메이션 효과 사용여부 선택 옵션
    private boolean isAnimated;					//애니메이션 효과 사용 여부


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_);

        mPageMark = (LinearLayout)findViewById(R.id.page_mark);			//상단의 현재 페이지 나타내는 뷰

        mPager = (ViewPager)findViewById(R.id.pager);						//뷰 페이저
        mPager.setAdapter(new BkFragmentAdapter(getSupportFragmentManager()));//PagerAdapter로 설정
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {    //아이템이 변경되면, gallery나 listview의 onItemSelectedListener와 비슷
            //아이템이 선택이 되었으면
            @Override
            public void onPageSelected(int position) {

                mPageMark.getChildAt(mPrevPosition).setBackgroundResource(R.drawable.page_not);    //이전 페이지에 해당하는 페이지 표시 이미지 변경
                mPageMark.getChildAt(position).setBackgroundResource(R.drawable.page_select);        //현재 페이지에 해당하는 페이지 표시 이미지 변경
                mPrevPosition = position;                //이전 포지션 값을 현재로 변경

            }

            @Override
            public void onPageScrolled(int position, float positionOffest, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        initPageMark();	//현재 페이지 표시하는 뷰 초기화


        isAnimated = true;		//기본적으로 애니메이션 사용.
    }

    //상단의 현재 페이지 표시하는 뷰 초기화
    private void initPageMark(){
        for(int i=0; i<COUNT; i++)
        {
            ImageView iv = new ImageView(getApplicationContext());	//페이지 표시 이미지 뷰 생성
            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            //첫 페이지 표시 이미지 이면 선택된 이미지로
            if(i==0)
                iv.setBackgroundResource(R.drawable.page_select);
            else	//나머지는 선택안된 이미지로
                iv.setBackgroundResource(R.drawable.page_not);

            //LinearLayout에 추가
            mPageMark.addView(iv);
        }
        mPrevPosition = 0;	//이전 포지션 값 초기화
    }

    private class BkFragmentAdapter extends FragmentPagerAdapter {

        public BkFragmentAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override public int getCount() { return COUNT; }	//여기서는 2개만 할 것이다.

        @Override
        public Fragment getItem(int position) {
            return ArrayFragment.newInstance(position);
        }

    }

    //뷰 페이저의 페이지에 맞는 fragment를 생성하는 객체
    private static class ArrayFragment extends Fragment {
        int mPosition;	//뷰 페이저의 페이지 값

        //fragment 생성하는 static 메소드 뷰페이저의 position을 값을 받는다.
        static ArrayFragment newInstance(int position) {
            ArrayFragment f = new ArrayFragment();	//객체 생성
            Bundle args = new Bundle();					//해당 fragment에서 사용될 정보 담을 번들 객체
            args.putInt("position", position);				//포지션 값을 저장
            f.setArguments(args);							//fragment에 정보 전달.
            return f;											//fragment 반환
        }

        //fragment가 만들어질 때
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPosition = getArguments() != null ? getArguments().getInt("position") : 0;	// 뷰페이저의 position값을  넘겨 받음
        }

        //fragment의 UI 생성
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.layout1+mPosition, container, false);	//미리 알고 있는 레이아웃을 inflate 한다.

            if(mPosition == 3) {
                v.findViewById(R.id.start_li);
                v.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        SharedPreferenceUtil.putSharedPreference(getActivity(), "intro", "Y");
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.start_enter, R.anim.start_exit);

                        getActivity().finish();
                    }

                });
            }

            return v;
        }
    }


}
