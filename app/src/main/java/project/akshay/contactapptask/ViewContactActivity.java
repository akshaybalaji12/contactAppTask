package project.akshay.contactapptask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewContactActivity extends AppCompatActivity {

    ImageView imageView;
    TextView displayName, displayNumber;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        Toolbar toolbar = findViewById(R.id.toolbar);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        imageView = findViewById(R.id.expandedImage);
        displayName = findViewById(R.id.displayName);
        displayNumber = findViewById(R.id.displayNumber);

        int picID = getIntent().getIntExtra(AppUtilities.EXTRA_PIC_ID,0);
        String name = getIntent().getStringExtra(AppUtilities.EXTRA_CONTACT_NAME);
        String mob = getIntent().getStringExtra(AppUtilities.EXTRA_CONTACT_NUMBER);

        displayName.setText(getResources().getString(R.string.displayName) + name);
        displayNumber.setText(getResources().getString(R.string.displayNumber) + mob);

        imageView.setTransitionName(name);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        imageView.setBackgroundResource(picID);
        toolbarLayout.setTitle(name);
        toolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.black));
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Functionality to change image could be added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
