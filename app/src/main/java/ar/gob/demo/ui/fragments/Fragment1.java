package ar.gob.demo.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ar.gob.demo.R;
import ar.gob.demo.helpers.Constants;
import ar.gob.demo.helpers.IntentHelper;
import ar.gob.demo.helpers.Permission;
import ar.gob.demo.helpers.PermissionsHelper;

public class Fragment1 extends BaseFragment {


    private TextView mTitle;
    private Button btnSendSms;
    private int mPage;
    private PermissionsHelper permissionHelper;
    private Permission permissionSendSms;
    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_send_sms:
                    if (!permissionHelper.checkPermission(permissionSendSms.getRequestCode())) {
                        permissionHelper.requestPermissionForSendSms();
                    } else {
                        IntentHelper.goToSmsSend(getActivity(), "", "");
                    }
                    break;
            }
        }
    };

    public static Fragment1 newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_PAGE, page);
        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(Constants.ARG_PAGE);
        permissionHelper = new PermissionsHelper(getActivity());
        permissionSendSms = Permission.SEND_SMS;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setContent();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragmento_uno;
    }

    public void init(View view) {
        mTitle = (TextView) view.findViewById(R.id.tvTitle);
        btnSendSms = (Button) view.findViewById(R.id.btn_send_sms);
        btnSendSms.setOnClickListener(btnOnClickListener);
    }

    public void setContent() {
        mTitle.setText("Fragment: " + mPage);
    }

}