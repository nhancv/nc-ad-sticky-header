package com.nhancv.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class StyleNonKeepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_non_keep);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Style Non Keep Header");
        }

        RecyclerView rvListItems = (RecyclerView) findViewById(R.id.rvListItems);
        final StyleNonKeepAdapter adapter = new StyleNonKeepAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListItems.setHasFixedSize(true);
        rvListItems.setNestedScrollingEnabled(false);
        rvListItems.setLayoutManager(linearLayoutManager);
        rvListItems.setItemAnimator(new DefaultItemAnimator());
        rvListItems.addItemDecoration(adapter.getSectionDecoration());
        rvListItems.setAdapter(adapter);

        List<String> sample = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            sample.add(String.valueOf(i));
        }

        adapter.setListsItems(sample);

        EditText etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.setSearchText(s.toString());
            }
        });

    }
}
