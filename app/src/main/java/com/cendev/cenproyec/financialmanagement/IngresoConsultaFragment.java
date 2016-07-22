package com.cendev.cenproyec.financialmanagement;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IngresoConsultaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IngresoConsultaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IngresoConsultaFragment extends Fragment {
    SimpleCursorAdapter mAdapter;
    ListView lv;

    private OnFragmentInteractionListener mListener;

    public IngresoConsultaFragment() {
        // Required empty public constructor
    }

    public static IngresoConsultaFragment newInstance() {
        IngresoConsultaFragment fragment = new IngresoConsultaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ingreso_consulta, container, false);
        lv = (ListView) v.findViewById(R.id.IngresoConsultaListView);

        // For the cursor adapter, specify which columns go into which views
        String[] fromColumns = {"_id", DatabaseContract.IngresoEntry.COLUMN_NAME_CANTIDAD};
        int[] toViews = {android.R.id.text1,android.R.id.text2}; // The TextView in simple_list_item_1

        // Create an empty adapter we will use to display the loaded data.
        Cursor listItems = CreateLoader();
        // TODO: seems like a custom adapter will be needed to add titles to numbers
        mAdapter = new SimpleCursorAdapter(this.getContext(), android.R.layout.simple_list_item_2, listItems, fromColumns, toViews, 0);
        lv.setAdapter(mAdapter);


        return v;
    }

    public Cursor CreateLoader() {
        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        SqliteController controller = new SqliteController(this.getContext());
        Cursor listItems = controller.listarIngresos();
        return listItems;
    }
    // Called when a previously created loader has finished loading
    public void onLoadFinished(Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        mAdapter.swapCursor(data);
    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset() {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
