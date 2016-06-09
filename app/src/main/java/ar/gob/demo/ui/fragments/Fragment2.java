package ar.gob.demo.ui.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ar.gob.demo.R;
import ar.gob.demo.helpers.AppApplication;
import ar.gob.demo.ui.adapters.ProcedureAdapter;

public class Fragment2 extends BaseFragment {

    private SwipeRefreshLayout swipeLayout;
    private ListView lv;
    private ProcedureAdapter adapter;
    private FloatingActionButton fab;
    private List<String> myArray;

    public static Fragment2 newInstance() {
        Fragment2 fragment = new Fragment2();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setContent();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragmento_dos;
    }

    public void init(View view) {
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        lv = (ListView) view.findViewById(R.id.listView);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    public void setContent() {
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        swipeLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorBackground, R.color.colorGreyLight, R.color.colorSecondary);
        adapter = new ProcedureAdapter(getActivity(), loadList());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Has seleccionado: " + myArray.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showDialog() {
        AppApplication.dialogHelper.showDialog(getActivity(), getString(R.string.dialogTitle), getString(R.string.dialogMessage), getString(R.string.accept), new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }, getString(R.string.cancel), new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }, false);
    }

    private List<String> loadList() {
        myArray = new ArrayList<>();
        myArray.add("Tramite 1");
        myArray.add("Tramite 2");
        myArray.add("Tramite 3");
        myArray.add("Tramite 4");
        return myArray;
    }

}