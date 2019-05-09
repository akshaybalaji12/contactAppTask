package project.akshay.contactapptask;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class AddContactActivity extends AppCompatActivity {

    TextInputEditText nameEditText, emailEditText, mobEditText, addressEditText, dobEditText, countryEditText;
    Button addContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Objects.requireNonNull(getSupportActionBar()).setElevation(10f);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setCustomView(R.layout.action_bar_add_contact);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        mobEditText = findViewById(R.id.mobEditText);
        dobEditText = findViewById(R.id.dobEditText);
        addressEditText = findViewById(R.id.addressEditText);
        countryEditText = findViewById(R.id.countryEditText);

        addContactButton = findViewById(R.id.addContactButton);

        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkEmpty())
                    addContactToDatabase();
                else
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.enterDetails),Toast.LENGTH_SHORT).show();
            }
        });



    }

    private boolean checkEmpty() {

        return nameEditText.getText().toString().equals("") ||
                emailEditText.getText().toString().equals("") ||
                mobEditText.getText().toString().equals("") ||
                countryEditText.getText().toString().equals("") ||
                addressEditText.getText().toString().equals("") ||
                dobEditText.getText().toString().equals("");

    }

    private void addContactToDatabase() {

        String name = Objects.requireNonNull(nameEditText.getText()).toString();
        String email = Objects.requireNonNull(emailEditText.getText()).toString();
        String dob = Objects.requireNonNull(dobEditText.getText()).toString();
        String address = Objects.requireNonNull(addressEditText.getText()).toString();
        String mob = Objects.requireNonNull(mobEditText.getText()).toString();
        String picID = Integer.toString(AppUtilities.getPic());

        ContactsDatabase contactsDatabase = new ContactsDatabase(getApplicationContext());
        Contacts contacts = new Contacts(name,email,mob,dob,address,picID);

        contactsDatabase.addContact(contacts);

        finishAffinity();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }
}
