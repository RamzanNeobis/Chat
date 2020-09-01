package ru.startandroid.develop.mychatapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.startandroid.develop.mychatapplication.interfases.OnItemClickListener;
import ru.startandroid.develop.mychatapplication.model.Chat;
import ru.startandroid.develop.mychatapplication.model.User;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {


    private List<Chat> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public ChatAdapter(Context context, List<Chat> list){
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

        public void bind(Chat chat) {
            textView.setText(chat.getId());
        }
    }
}
