package ru.startandroid.develop.mychatapplication.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.startandroid.develop.mychatapplication.R;
import ru.startandroid.develop.mychatapplication.interfases.OnItemClickListener;
import ru.startandroid.develop.mychatapplication.model.User;

public class ContactsAdapter  extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {


    private List<User> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public ContactsAdapter(Context context, List<User> list){
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  inflater.inflate(R.layout.list_user, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            textView = itemView.findViewById(R.id.textView);
        }

        public void bind(User user) {
            textView.setText(user.getName());
        }
    }
}
