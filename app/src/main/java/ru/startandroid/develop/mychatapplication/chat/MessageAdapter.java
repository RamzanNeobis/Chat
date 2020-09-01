package ru.startandroid.develop.mychatapplication.chat;

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
import ru.startandroid.develop.mychatapplication.model.Message;
import ru.startandroid.develop.mychatapplication.model.User;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {


    private List<Message> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public MessageAdapter(Context context, List<Message> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

        public void bind(Message message) {
            textView.setText(message.getText());
        }
    }
}
