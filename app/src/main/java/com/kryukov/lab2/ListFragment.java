package com.kryukov.lab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kryukov.lab2.adapter.TechListAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private TechListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tech_list_fragment, null);

        recyclerView = view.findViewById(R.id.tech_list);

        recyclerView.setItemAnimator(null);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        TechActivity activity = (TechActivity) getActivity();

        adapter = new TechListAdapter(activity);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy != 0) {
                    assert activity != null;
                    activity.requestGraphicForVisible();
                }
            }
        });

        assert activity != null;
        recyclerView.post(activity::requestGraphicForVisible);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getArguments() != null)
            recyclerView.scrollToPosition(getArguments().getInt("arg_current_item"));
    }

    public TechListAdapter getAdapter() {
        return adapter;
    }

    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

}
