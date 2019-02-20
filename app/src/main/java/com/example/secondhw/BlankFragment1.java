package com.example.secondhw;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BlankFragment1 extends Fragment {

    private static final String doc_count = "doc_count";

    @SuppressLint("DefaultLocale")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_fragment1, container, false);
        TextView doc = view.findViewById(R.id.doc);
        int docCount = getArguments().getInt(doc_count);
        doc.setText(String.format("документ %d", docCount));
        return view;

    }

    public static BlankFragment1 newInstance(int documentCount) {
        Bundle bundle = new Bundle();
        bundle.putInt(doc_count,documentCount);
        BlankFragment1 blankFragment1 = new BlankFragment1();
        blankFragment1.setArguments(bundle);
        return blankFragment1;
    }




}
