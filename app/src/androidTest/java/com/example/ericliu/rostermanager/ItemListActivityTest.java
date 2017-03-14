package com.example.ericliu.rostermanager;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.data.entity.BusinessInfo;
import com.example.data.entity.FinishedShift;
import com.example.data.entity.Shift;
import com.example.ericliu.rostermanager.data.api.BusinessInfoService;
import com.example.ericliu.rostermanager.data.api.ShiftService;
import com.example.ericliu.rostermanager.ui.ItemListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.Completable;
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

    private ShiftService shiftService;

    @Rule
    public ActivityTestRule<ItemListActivity> mActivityTestRule =
            new ActivityTestRule<>(ItemListActivity.class);
    private Shift shift;


    @Before
    public void setUp() throws Exception {
        businessInfoService = new Retrofit.Builder()
                .baseUrl(PRODUCTION_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(BusinessInfoService.class);

        shiftService = new Retrofit.Builder()
                .baseUrl(PRODUCTION_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ShiftService.class);


        shift = new Shift();
        shift.time = "2017-01-17T06:35:57+00:00";
        shift.latitude = "0.00000";
        shift.longitude = "0.00000";
    }


    @Test
    public void testGetBusinessInfo() throws Exception {
        Observable<BusinessInfo> businessInfoObservable = businessInfoService.businessInfo();
        TestObserver<BusinessInfo> testObserver = new TestObserver<>();
        businessInfoObservable.subscribe(testObserver);
        testObserver.assertNoErrors();
    }


    @Test
    public void shouldStartAShift() throws Exception {

        Completable completable = shiftService.startAShift(shift);
        TestObserver testObserver = new TestObserver<>();

        completable.subscribe(testObserver);

        testObserver.assertNoErrors();
    }

    @Test
    public void shouldGetPreviousShifts() throws Exception {
        shiftService.startAShift(shift);
        shiftService.endAShift(shift);

        Observable<List<FinishedShift>> observable = shiftService.getPreviousShifts();
        TestObserver<List<FinishedShift>> testObserver = new TestObserver<>();
        observable.subscribe(testObserver);
        testObserver.assertNoErrors();
    }
}