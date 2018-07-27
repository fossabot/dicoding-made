package com.kevinhermawan.learnfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailCategoryFragment extends Fragment implements View.OnClickListener {

    private TextView tvCategoryName, tvCategoryDesc;
    private Button btnProfile, btnShowDialog;
    public static String EXTRA_NAME = "extra_name";
    private String description;

    public DetailCategoryFragment() {
        // Required empty public constructor
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_category, container, false);
        tvCategoryName = (TextView) view.findViewById(R.id.tvCategoryName);
        tvCategoryDesc = (TextView) view.findViewById(R.id.tvCategoryDesc);
        btnProfile = (Button) view.findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(this);
        btnShowDialog = (Button) view.findViewById(R.id.btnShowDialog);
        btnShowDialog.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String categoryName = getArguments().getString(EXTRA_NAME);
        tvCategoryName.setText(categoryName);
        tvCategoryDesc.setText(getDescription());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnProfile:
                Intent mIntent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btnShowDialog:
                OptionDialogFragment mOptionDialogFragment = new OptionDialogFragment();
                mOptionDialogFragment.setOnOptionDialogListener(new OptionDialogFragment.OnOptionDialogListener() {
                    @Override
                    public void onOptionChoosen(String text) {
                        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                    }
                });

                FragmentManager mFragmentManager = getFragmentManager();
                mOptionDialogFragment.show(mFragmentManager, OptionDialogFragment.class.getSimpleName());
                break;
        }
    }

}
