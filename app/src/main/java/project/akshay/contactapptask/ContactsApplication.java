package project.akshay.contactapptask;

import android.app.Application;

public class ContactsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtilities.setDefaultFont(this, "SERIF", "fonts/Product-Sans-Bold.ttf");
    }
}
