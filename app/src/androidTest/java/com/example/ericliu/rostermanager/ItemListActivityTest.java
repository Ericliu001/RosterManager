package com.example.ericliu.rostermanager;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.data.entity.BusinessInfo;
import com.example.ericliu.rostermanager.data.api.BusinessInfoService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.ericliu.rostermanager.dagger.ApiModule.PRODUCTION_API_URL;

/**
 * Created by ericliu on 10/3/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ItemListActivityTest {

    private BusinessInfoService businessInfoService;

    @Rule
    public ActivityTestRule<ItemListActivity> mActivityTestRule =
            new ActivityTestRule<>(ItemListActivity.class);


    @Before
    public void setUp() throws Exception {
        businessInfoService = new  Retrofit.Builder().baseUrl(PRODUCTION_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(BusinessInfoService.class);
    }


    @Test
    public void testGetBusinessInfo() throws Exception {
        Observable<BusinessInfo> businessInfoObservable = businessInfoService.businessInfo();
        TestObserver<BusinessInfo> testObserver = new TestObserver<>();
        businessInfoObservable.subscribe(testObserver);
        testObserver.assertNoErrors();
    }
}