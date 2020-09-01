package ru.startandroid.develop.mychatapplication.chat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.startandroid.develop.mychatapplication.R;
import ru.startandroid.develop.mychatapplication.contacts.ContactsActivity;
import ru.startandroid.develop.mychatapplication.contacts.ContactsAdapter;
import ru.startandroid.develop.mychatapplication.interfases.OnItemClickListener;
import ru.startandroid.develop.mychatapplication.model.Chat;
import ru.startandroid.develop.mychatapplication.model.Message;
import ru.startandroid.develop.mychatapplication.model.User;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    private List<Message> list = new ArrayList<>();
    private EditText editText;
    private User user;
    private Chat chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recyclerView);
        user = (User) getIntent().getSerializableExtra("user");
        chat = (Chat) getIntent().getSerializableExtra("chat");
        if (chat == null) {
            chat = new Chat();
            ArrayList<String> userIds = new ArrayList<>();
            userIds.add(user.getId());
            userIds.add(FirebaseAuth.getInstance().getUid());
            chat.setUserId(userIds);
        } else {
            initList();
            getMessages();
        }

    }


    private void initList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new MessageAdapter(this, list);
        recyclerView.setAdapter(adapter);

    }

    private void getMessages() {
        FirebaseFirestore.getInstance().collection("chats")
                .document(chat.getId())
                .collection("messages")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                for(DocumentChange change: snapshots.getDocumentChanges()){
                    switch (change.getType()){
                        case ADDED:
                            list.add(change.getDocument().toObject(Message.class));
                            break;
                        case MODIFIED:
                            break;
                        case REMOVED:
                            break;
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


    public void onClickSend(View view) {
        String text = editText.getText().toString().trim();
        if (chat.getId() != null) {
            sendMessage(text);
        } else {
            createChat(text);
        }

    }


    private void sendMessage(String text) {
        Map<String, Object> map = new HashMap<>();
        map.put("text", text);
        FirebaseFirestore.getInstance().collection("chats")
                .document(chat.getId())
                .collection("messages")
                .add(map);
    }

    private void createChat(final String text) {
        FirebaseFirestore.getInstance()
                .collection("chats")
                .add(chat)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        chat.setId(documentReference.getId());
                        sendMessage(text);
                    }
                });
    }


}