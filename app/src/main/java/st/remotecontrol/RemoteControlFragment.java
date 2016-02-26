package st.remotecontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
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

        // set number buttons
        TableLayout tableLayout =
                (TableLayout) view.findViewById(R.id.remote_control_table);
        int number = 1;
        for (int i = 2; i < tableLayout.getChildCount() - 1; i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                Button button = (Button) row.getChildAt(j);
                button.setText(String.valueOf(number));
                button.setOnClickListener(numberButtonListener);
                number++;
            }
        }

        // set enter, zero and enter buttons
        TableRow bottomRow = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount() - 1);

        Button delete = (Button) bottomRow.getChildAt(0);
        delete.setText("Delete");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workingTextView.setText("0");
            }
        });

        Button zero = (Button) bottomRow.getChildAt(1);
        zero.setText("0");
        zero.setOnClickListener(numberButtonListener);

        Button enter = (Button) bottomRow.getChildAt(2);
        enter.setText("Enter");
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
