package edu.chinmayt.cs478.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class editText extends AppCompatActivity {
    protected static final String nameKey = "NAME_VALUE" ;
    private String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        /*if (savedInstanceState == null) {
            name = "";
        }
        else {
            name = savedInstanceState.getString(nameKey) ;
            Log.i("editText", "Saved state retrieved with " + name ) ;
        }*/
        EditText updatedText = (EditText) findViewById(R.id.editTextTextPersonName);
        updatedText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == 0 && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                //Getting value from text view when pressed done
                name = v.getText().toString();
                Log.d("Name ", v.getText().toString());
            }return true;
        });

    }
    protected void onPause() {
        super.onPause();
    }
    @Override
    public void onBackPressed() {

        EditText updatedText = (EditText) findViewById(R.id.editTextTextPersonName);
        //Getting value from text view when pressed back button
        name = updatedText.getText().toString();

        Intent i = new Intent();
        i.putExtra("1", name);

        Log.i("onBackPressed", "name: "+ name);
        //if (name.trim().matches("[a-zA-Z][a-zA-Z ]+[a-zA-Z]$")){
        if (name.trim().matches("^[a-zA-Z]+(?:[a-zA-Z ]+)+$")){
            setResult(RESULT_OK, i);
        } else {
            setResult(RESULT_CANCELED, i);
        }
        Log.i("editText", "onBackPressed: "+ i.getStringExtra("1"));
        super.onBackPressed();
    }
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState)  ;
        outState.putString(nameKey, name) ;
    }
}