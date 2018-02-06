package com.example.quynh.cst2335_lab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends Activity {
    private ListView listview;
    private Button button;
    private EditText editText;
     ArrayList<String> arr;
     ChatAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        listview = findViewById(R.id.listview);
        button= findViewById(R.id.button_send);
        editText=findViewById(R.id.edittext);
        arr = new ArrayList<>();
        messageAdapter =new ChatAdapter( this );
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String content = editText.getText().toString();
                arr.add(content);
                listview.setAdapter (messageAdapter);
                messageAdapter.notifyDataSetChanged();
                editText.setText("");
//                Intent intent = new Intent(String.valueOf(StartActivity.class));
            }
        });
    }
    private class ChatAdapter extends ArrayAdapter<String>{
       public Context context;
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }
        public int getCount(){
            return arr.size();
        }
        public String getItem(int position){
            return arr.get(position);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null ;
            if(position % 2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(   getItem(position)  ); // get the string at position
            return result;


        }
        public long getId(int position){
            return position;
        }
    }
}