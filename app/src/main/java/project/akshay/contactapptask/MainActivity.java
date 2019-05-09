package project.akshay.contactapptask;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RecyclerView contactListView;
    FloatingActionButton addContactButton;
    ArrayList<Contacts> contactsDetails = new ArrayList<>();
    ContactsListAdapter contactsListAdapter;
    LinearLayoutManager manager;
    DividerItemDecoration decoration;

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
        contactsListAdapter = new ContactsListAdapter(contactsDetails);

        contactListView.setAdapter(contactsListAdapter);
        contactListView.setLayoutManager(manager);

        for(int i=0;i<500;i++){
            Contacts contact;
            if(i%2==0)
                contact  = new Contacts("Bruce Wayne");
            else
                contact = new Contacts("John Wick");

            contactsDetails.add(contact);
            contactsListAdapter.notifyDataSetChanged();
        }

        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddContactActivity.class));
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });

    }
}
