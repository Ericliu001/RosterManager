package com.example.presentation.presenter;

import com.example.AndroidWapper;
import com.example.data.entity.BusinessInfo;
import com.example.data.entity.FinishedShift;
import com.example.data.repository.BusinessInfoRepository;
import com.example.data.repository.ShiftRepository;
import com.example.presentation.view.ItemListActivityView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ericliu on 14/3/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemListActivityPresenterTest {

    private static final String BUSINESS_LOGO_URL = "http:something.logo";
    private static final String BUSINESS_NAME = "McDonald";

    @Mock
    BusinessInfoRepository businessInfoRepository;

    @Mock
    ShiftRepository shiftRepository;

    @Mock
    ItemListActivityView itemListActivityView;

    private ItemListActivityPresenter itemListActivityPresenter;

    @Before
    public void setUp() throws Exception {
        final BusinessInfo businessInfo = new BusinessInfo(BUSINESS_NAME, BUSINESS_LOGO_URL);
        final List<FinishedShift> finishedShifts = new ArrayList<>();
        final FinishedShift finishedShift1 = new FinishedShift();
        finishedShifts.add(finishedShift1);
        AndroidWapper androidWapper = Mockito.mock(AndroidWapper.class);

        when(businessInfoRepository.getBusinessInfo()).thenReturn(Observable.just(businessInfo));
        when(shiftRepository.getPreviousShifts()).thenReturn(Observable.just(finishedShifts));
        when(androidWapper.getMainThreadScheduler()).thenReturn(Schedulers.trampoline());

        itemListActivityPresenter = new ItemListActivityPresenter(androidWapper, businessInfoRepository, shiftRepository);
        itemListActivityPresenter.onCreate(itemListActivityView);
    }

    @Test
    public void onViewCreated() throws Exception {

    }

    @Test
    public void shouldDisplayBusinessNameAndLogoOnResume() throws Exception {
        itemListActivityPresenter.onResume();
        verify(itemListActivityView).showBusinessName(BUSINESS_NAME);
        verify(itemListActivityView).showBusinessLogo(BUSINESS_LOGO_URL);
    }


    @Test
    public void onDestroy() throws Exception {

    }

}