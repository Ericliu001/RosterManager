package com.example.ericliu.rostermanager.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.data.entity.FinishedShift;
import com.example.ericliu.rostermanager.R;
import com.example.ericliu.rostermanager.dagger.BaseActivityComponent;
import com.example.presentation.presenter.BasePresenter;
import com.example.presentation.presenter.ItemListActivityPresenter;
import com.example.presentation.view.ItemListActivityView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends BaseActivity implements ItemListActivityView {

    @Inject
    ItemListActivityPresenter presenter;


    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private List<FinishedShift> finishedShiftList = Collections.emptyList();

    private ViewGroup currentShiftContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        currentShiftContainer = (ViewGroup) findViewById(R.id.currentShiftContainer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Shift Started!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                presenter.onStartShiftButtonClick();
            }
        });

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    @Override
    protected void inject(final BaseActivityComponent component) {
        component.inject(this);
    }

    @Override
    protected BasePresenter attachPresenter() {
        return presenter;
    }


    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter());
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = finishedShiftList.get(position);
            holder.mIdView.setText(finishedShiftList.get(position).id);
            holder.mContentView.setText(finishedShiftList.get(position).start);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return finishedShiftList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public FinishedShift mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }


    @Override
    public void showBusinessName(final String name) {
        getSupportActionBar().setTitle(name);
    }

    @Override
    public void showBusinessLogo(final String logoUrl) {
        Glide.with(this).load(logoUrl).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(final GlideDrawable resource, final GlideAnimation<? super GlideDrawable> glideAnimation) {
                getSupportActionBar().setLogo(resource);
            }
        });
    }

    @Override
    public void showFinishedShiftsList(final List<FinishedShift> finishedShifts) {
        this.finishedShiftList = finishedShifts;
    }

    @Override
    public void showStartedShiftLayoutWithProgressBar() {
        currentShiftContainer.setVisibility(View.VISIBLE);
    }
}
