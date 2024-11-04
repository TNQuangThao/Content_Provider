package com.example.contentprovider;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager2 viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setIcon(R.drawable.icons8_sms_48);
                    break;
                case 1:
                    tab.setIcon(R.drawable.icons8_call_50);
                    break;
                case 2:
                    tab.setIcon(R.drawable.icons8_contact_48);
                    break;
            }
        }).attach();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("f" + position);

                if (currentFragment != null && currentFragment.isVisible()) {
                    if (currentFragment instanceof SmsFragment) {
                        ((SmsFragment) currentFragment).loadSmsData(); // Hàm custom để load lại SMS
                    } else if (currentFragment instanceof CallLogFragment) {
                        ((CallLogFragment) currentFragment).loadCallLogData(); // Hàm custom để load lại nhật ký cuộc gọi
                    } else if (currentFragment instanceof ContactFragment) {
                        ((ContactFragment) currentFragment).loadContactData(); // Hàm custom để load lại danh bạ
                    }
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("f" + position);
                if (currentFragment != null && currentFragment.isVisible()) {
                    if (currentFragment instanceof SmsFragment) {
                        ((SmsFragment) currentFragment).loadSmsData();
                    } else if (currentFragment instanceof CallLogFragment) {
                        ((CallLogFragment) currentFragment).loadCallLogData();
                    } else if (currentFragment instanceof ContactFragment) {
                        ((ContactFragment) currentFragment).loadContactData();
                    }
                }
            }
        });

    }
}