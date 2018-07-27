package com.kevinhermawan.learnfragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionDialogFragment extends DialogFragment implements View.OnClickListener {

    private Button btnChoose, btnClose;
    private RadioGroup rgOptions;
    private RadioButton rbSAF, rbMOU, rbLVG, rbMoyes;
    private OnOptionDialogListener onOptionDialogListener;

    public OptionDialogFragment() {
        // Required empty public constructor
    }

    public OnOptionDialogListener getOnOptionDialogListener() {
        return onOptionDialogListener;
    }

    public void setOnOptionDialogListener(OnOptionDialogListener onOptionDialogListener) {
        this.onOptionDialogListener = onOptionDialogListener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_option_dialog, container, false);
        btnChoose = (Button)view.findViewById(R.id.btnChoose);
        btnChoose.setOnClickListener(this);
        btnClose = (Button)view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
        rgOptions = (RadioGroup)view.findViewById(R.id.rgOptions);
        rbSAF = (RadioButton)view.findViewById(R.id.rbSAF);
        rbLVG = (RadioButton)view.findViewById(R.id.rbLVG);
        rbMOU = (RadioButton)view.findViewById(R.id.rbMOU);
        rbMoyes = (RadioButton)view.findViewById(R.id.rbMoyes);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnClose:
                getDialog().cancel();
                break;
            case R.id.btnChoose:
                int checkedRadioButtonId = rgOptions.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1) {
                    String coach = null;
                    switch (checkedRadioButtonId) {
                        case R.id.rbSAF:
                            coach = rbSAF.getText().toString().trim();
                            break;
                        case R.id.rbMOU:
                            coach = rbMOU.getText().toString().trim();
                            break;
                        case R.id.rbLVG:
                            coach = rbLVG.getText().toString().trim();
                            break;
                        case R.id.rbMoyes:
                            coach = rbMoyes.getText().toString().trim();
                            break;
                    }
                    getOnOptionDialogListener().onOptionChoosen(coach);
                    getDialog().cancel();
                }
                break;
        }
    }

    public interface OnOptionDialogListener {
        void onOptionChoosen(String text);
    }

}
