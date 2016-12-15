package ca.kitamura.simpleaddressbook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ca.kitamura.simpleaddressbook.R;
import ca.kitamura.simpleaddressbook.models.randomuser.Result;

/**
 * Created by Darren on 2016-12-13.
 */

public class AddressBookAdapter extends RecyclerView.Adapter<AddressBookAdapter.AddressViewHolder>{

    private List<Result> contactList;
    RandomUserClickListener randomUserClickListener;

    public AddressBookAdapter(List<Result> contactList, RandomUserClickListener clickListener) {
        this.contactList = contactList;
        this.randomUserClickListener = clickListener;
    }

    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_address_book_row, parent, false);
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddressViewHolder holder, int position) {
        final Result tempResult = contactList.get(position);
        Glide.with(holder.contactImage.getContext()).load(tempResult.getPicture().getThumbnail()).placeholder(R.drawable.ic_face_black_24dp).into(holder.contactImage);
        holder.contactName.setText(String.format("%s %s", tempResult.getName().getFirst(), tempResult.getName().getLast()));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomUserClickListener.randomUserClicked(tempResult);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void setContactList(List<Result> newContactList) {
        this.contactList = newContactList;
    }


    static class AddressViewHolder extends RecyclerView.ViewHolder {
        ImageView contactImage;
        TextView contactName;
        LinearLayout parentLayout;

        public AddressViewHolder(View itemView) {
            super(itemView);
            contactImage = (ImageView)itemView.findViewById(R.id.list_address_book_row_user_imageview);
            contactName = (TextView)itemView.findViewById(R.id.list_address_book_row_name_textview);
            parentLayout = (LinearLayout)itemView.findViewById(R.id.list_address_book_parent_layout);
        }
    }


}
