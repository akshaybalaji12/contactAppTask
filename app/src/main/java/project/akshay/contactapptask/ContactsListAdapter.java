package project.akshay.contactapptask;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.MyViewHolder> {

    public ArrayList<Contacts> contactsArrayList;

    public ContactsListAdapter(ArrayList<Contacts> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contact_view,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.contactName.setText(contactsArrayList.get(i).getName());
        myViewHolder.contactImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        myViewHolder.contactImage.setBackgroundResource(contactsArrayList.get(i).getPicId());

    }

    @Override
    public int getItemCount() {
        return contactsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView contactName;
        ImageView contactImage;
        CardView container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            contactName = itemView.findViewById(R.id.contact_name);
            contactImage = itemView.findViewById(R.id.contactImage);
            container = itemView.findViewById(R.id.container);
            Typeface face = Typeface.createFromAsset(itemView.getContext().getAssets(),
                    "fonts/JosefinSans-Regular.ttf");
            contactName.setTypeface(face);

        }
    }
}
