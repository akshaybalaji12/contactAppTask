package project.akshay.contactapptask;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RecyclerView contactListView;
    FloatingActionButton addContactButton;
    ArrayList<Contacts> contactsDetails = new ArrayList<>();
    ContactsListAdapter contactsListAdapter;
    LinearLayoutManager manager;
    ContactsDatabase contactsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setElevation(10f);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        addContactButton = findViewById(R.id.addContact);
        contactListView = findViewById(R.id.contactsListView);
        manager = new LinearLayoutManager(getApplicationContext());

        contactsDatabase = new ContactsDatabase(this);

        contactsDetails = contactsDatabase.getAllContacts();
        if(contactsDetails.isEmpty()){
            setContentView(R.layout.no_contacts_view);
            FloatingActionButton addContactButton = findViewById(R.id.addContactButton);
            addContactButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),AddContactActivity.class));
                    overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                }
            });
        }
        contactsListAdapter = new ContactsListAdapter(contactsDetails);

        contactListView.setAdapter(contactsListAdapter);
        contactListView.setLayoutManager(manager);


        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddContactActivity.class));
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });

        contactsListAdapter.setOnContactClickListener(new OnContactClickListener() {
            @Override
            public void onContactClick(ImageView imageView, int picID, String name) {
                Intent intent = new Intent(getApplicationContext(),ViewContactActivity.class);
                intent.putExtra(AppUtilities.EXTRA_PIC_ID,picID);
                intent.putExtra(AppUtilities.EXTR_CONTACT_NAME,name);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(MainActivity.this,imageView,Objects.requireNonNull(ViewCompat.getTransitionName(imageView)));
                startActivity(intent,activityOptionsCompat.toBundle());
            }
        });

    }

    public interface OnContactClickListener{

        void onContactClick(ImageView imageView, int picID, String name);

    }

}
