package project.akshay.contactapptask;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.Random;

public class AppUtilities {

    public static final String STRING_SUCCESSFUL = "SUCCESSFULLY";
    public static final String EXTRA_PIC_ID = "EXTRA_PIC_ID";
    public static final String EXTR_CONTACT_NAME = "EXTRA_CONTACT_NAME";

    private static final int[] picsArray = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h};

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    private static void replaceFont(String staticTypefaceFieldName,
                                    final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getPic(){
        double randomNumber = Math.random();
        randomNumber = randomNumber * 8;
        int index = (int) randomNumber;
        return picsArray[index];
    }

    public static void printLogMessages(String tag, String message){
        Log.d(tag,message);
    }

}
