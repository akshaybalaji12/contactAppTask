package project.akshay.contactapptask;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RecyclerView contactListView;
    FloatingActionButton addContactButton;
    ArrayList<Contacts> contactsDetails = new ArrayList<>();
    ContactsListAdapter contactsListAdapter;
    LinearLayoutManager manager;
    RelativeLayout relativeLayout;
    SwipeRefreshLayout swipeRefreshLayout;

    private int PERMISSION_REQUEST_CODE_READ_CONTACTS = 1;
    private int PERMISSION_REQUEST_CODE_WRITE_CONTACTS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setElevation(10f);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        swipeRefreshLayout = findViewById(R.id.swipeToRefreshLayout);
        relativeLayout = findViewById(R.id.relativeLayout);
        addContactButton = findViewById(R.id.addContact);
        contactListView = findViewById(R.id.contactsListView);
        manager = new LinearLayoutManager(getApplicationContext());
        contactsListAdapter = new ContactsListAdapter(contactsDetails);

        contactListView.setAdapter(contactsListAdapter);
        contactListView.setLayoutManager(manager);

        if(!hasPhoneContactsPermission(Manifest.permission.READ_CONTACTS)) {
            requestPermission(Manifest.permission.READ_CONTACTS, PERMISSION_REQUEST_CODE_READ_CONTACTS);
        } else {
            displayPhoneContacts();
            Snackbar.make(relativeLayout,"Swipe to update contacts",Snackbar.LENGTH_SHORT).show();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finishAffinity();
                getApplicationContext().startActivity(MainActivity.this.getIntent());
                overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
            }
        });

        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hasPhoneContactsPermission(Manifest.permission.WRITE_CONTACTS))
                {
                    requestPermission(Manifest.permission.WRITE_CONTACTS, PERMISSION_REQUEST_CODE_WRITE_CONTACTS);
                }else
                {
                    startActivity(new Intent(getApplicationContext(),AddContactActivity.class));
                    overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                }
            }
        });

        contactsListAdapter.setOnContactClickListener(new OnContactClickListener() {
            @Override
            public void onContactClick(ImageView imageView, int picID, String name, String mob) {
                Intent intent = new Intent(getApplicationContext(),ViewContactActivity.class);
                intent.putExtra(AppUtilities.EXTRA_PIC_ID,picID);
                intent.putExtra(AppUtilities.EXTRA_CONTACT_NAME,name);
                intent.putExtra(AppUtilities.EXTRA_CONTACT_NUMBER,mob);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(MainActivity.this,imageView,Objects.requireNonNull(ViewCompat.getTransitionName(imageView)));
                startActivity(intent,activityOptionsCompat.toBundle());
            }
        });

    }

    private void displayPhoneContacts() {

        HashSet<String> checkNamePresent = new HashSet<>();
        Uri readContactsUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(readContactsUri,null,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        if(cursor != null){
            cursor.moveToFirst();

            do {
                int displayNameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String displayName = cursor.getString(displayNameIndex);
                int contactNumberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String contactNumber = cursor.getString(contactNumberIndex);
                if(checkNamePresent.add(displayName)){
                    Contacts contacts = new Contacts(displayName,contactNumber, Integer.toString(AppUtilities.getPic()));
                    contactsDetails.add(contacts);
                    contactsListAdapter.notifyDataSetChanged();
                }

            } while (cursor.moveToNext());

        }

    }

    private boolean hasPhoneContactsPermission(String permission) {
        boolean checkPermission = false;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasPermission = ContextCompat.checkSelfPermission(getApplicationContext(), permission);
            if (hasPermission == PackageManager.PERMISSION_GRANTED) {
                checkPermission = true;
            }
        }else {
            checkPermission = true;
        }
        return checkPermission;
    }

    private void requestPermission(String permission, int requestCode) {
        String requestPermissionArray[] = {permission};
        ActivityCompat.requestPermissions(this, requestPermissionArray, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        int length = grantResults.length;
        if(length > 0) {
            int grantResult = grantResults[0];

            if(grantResult == PackageManager.PERMISSION_GRANTED) {

                if(requestCode==PERMISSION_REQUEST_CODE_READ_CONTACTS) {
                    displayPhoneContacts();
                    Snackbar.make(relativeLayout,"Swipe to update contacts",Snackbar.LENGTH_SHORT).show();
                }else if(requestCode==PERMISSION_REQUEST_CODE_WRITE_CONTACTS) {
                    startActivity(new Intent(getApplicationContext(),AddContactActivity.class));
                    overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                }
            }else {
                Snackbar.make(relativeLayout,"Provide permission to read or write contacts",Snackbar.LENGTH_LONG).show();
            }
        }
    }

    public interface OnContactClickListener{

        void onContactClick(ImageView imageView, int picID, String name, String mob);

    }

}
