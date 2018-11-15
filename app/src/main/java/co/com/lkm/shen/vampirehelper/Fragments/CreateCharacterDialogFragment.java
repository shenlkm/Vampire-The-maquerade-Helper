package co.com.lkm.shen.vampirehelper.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Contracts.NoticeDialogListener;
import co.com.lkm.shen.vampirehelper.R;

public class CreateCharacterDialogFragment extends DialogFragment {

    @BindView(R.id.spinner_clans) Spinner mSpiner;

    private NoticeDialogListener mNoticeDialogListener;

    public void setListener(NoticeDialogListener noticeDialogListener){
        mNoticeDialogListener = noticeDialogListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.fragment_dialog_create_character, null);
        ButterKnife.bind(this, view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.clan_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpiner.setAdapter(adapter);

        builder.setView(view);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mNoticeDialogListener.onDialogNegativeClick(CreateCharacterDialogFragment.this);
                CreateCharacterDialogFragment.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mNoticeDialogListener = (NoticeDialogListener) getActivity().getFragmentManager().findFragmentById(R.id.fragment_dialog_create_character);
        }catch (ClassCastException ex){
            throw new ClassCastException(context.toString() + " must implement NoticeDialogListener");

        }
    }

    public static CreateCharacterDialogFragment newInstance(NoticeDialogListener listener){
        CreateCharacterDialogFragment instance = new CreateCharacterDialogFragment();
        instance.mNoticeDialogListener = listener;
        return instance;
    }
}
