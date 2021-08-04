package edu.chinmayt.cs478.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected Button contacts ;
    protected Button editText1;
    protected String phoneNumber ;
    protected int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = (Button) findViewById(R.id.button2) ;
        editText1 = (Button) findViewById(R.id.button1) ;

        contacts.setOnClickListener(contactListener) ;
        editText1.setOnClickListener(editListener) ;
    }
    public View.OnClickListener editListener = v -> switchToEditText();
    //public TextView.OnEditorActionListener editListener = v -> switchToEditText();


    private void switchToEditText() {
        Intent i = new Intent(MainActivity.this, editText.class) ;
        startActivityForResult(i,1);
    }

    protected void onActivityResult(int code, int result_code, Intent i) {
        super.onActivityResult(code, result_code, i);
        if(result_code == -1) {
            Log.i("MainActivity: ", "Returned result is: " + result_code);
            Log.i("MainActivity: ", "My result code returned " + code);
            Log.i("MainActivity: ", "Info returned " + i.getStringExtra("1"));
        } else {
            Log.i("MainActivity", "onActivityResult: "+ result_code);
        }
        phoneNumber = i.getStringExtra("1");
        result = result_code;
    }
    View.OnClickListener contactListener = v -> switchToContact(phoneNumber,result); //Call to Contacts app installed on phone
    private void switchToContact(String phoneNumber, int result) {
        if(result == -1) {
            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.NAME, phoneNumber);
            startActivity(intent);
        } else {
            Toast.makeText(this, "You have entered an incorrect name: " + phoneNumber, Toast.LENGTH_SHORT).show();
        }
    }
}