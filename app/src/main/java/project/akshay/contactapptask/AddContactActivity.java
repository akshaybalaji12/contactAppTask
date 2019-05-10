package project.akshay.contactapptask;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class AddContactActivity extends AppCompatActivity {

    TextInputEditText nameEditText, mobEditText;
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
        mobEditText = findViewById(R.id.mobEditText);

        addContactButton = findViewById(R.id.addContactButton);

        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkEmpty())
                    addContactToMobile();
                else
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.enterDetails),Toast.LENGTH_SHORT).show();
            }
        });



    }

    private boolean checkEmpty() {

        return nameEditText.getText().toString().equals("") ||
                mobEditText.getText().toString().equals("");

    }

    private void addContactToMobile() {

        String name = Objects.requireNonNull(nameEditText.getText()).toString();
        String mob = Objects.requireNonNull(mobEditText.getText()).toString();

        Uri addContactsUri = ContactsContract.Data.CONTENT_URI;
        ContentValues contentValues = new ContentValues();
        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contentValues);
        long rawContactId = ContentUris.parseId(rawContactUri);

        insertName(addContactsUri,rawContactId,name);
        insertPhoneNumber(addContactsUri,rawContactId,mob);

        finishAffinity();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

    }

    private void insertPhoneNumber(Uri addContactsUri, long rawContactId, String mob) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        contentValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, mob);
        getContentResolver().insert(addContactsUri, contentValues);

    }

    private void insertName(Uri addContactsUri, long rawContactId, String name) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        contentValues.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
        getContentResolver().insert(addContactsUri, contentValues);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }
}
