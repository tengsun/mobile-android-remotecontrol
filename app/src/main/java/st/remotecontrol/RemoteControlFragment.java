package st.remotecontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RemoteControlFragment extends Fragment {

    private TextView selectedTextView;
    private TextView workingTextView;

    public RemoteControlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote_control, container, false);

        selectedTextView = (TextView) view.findViewById(R.id.remote_control_selected);
        workingTextView = (TextView) view.findViewById(R.id.remote_control_working);

        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;
                String text = textView.getText().toString();
                String working = workingTextView.getText().toString();
                if (working.equals("0")) {
                    workingTextView.setText(text);
                } else {
                    workingTextView.setText(working + text);
                }
            }
        };

        Button zero = (Button) view.findViewById(R.id.remote_control_zero);
        zero.setOnClickListener(numberButtonListener);
        Button one = (Button) view.findViewById(R.id.remote_control_one);
        one.setOnClickListener(numberButtonListener);
        Button enter = (Button) view.findViewById(R.id.remote_control_enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence working = workingTextView.getText();
                if (working.length() > 0) {
                    selectedTextView.setText(working);
                }
                workingTextView.setText("0");
            }
        });

        return view;
    }

}
