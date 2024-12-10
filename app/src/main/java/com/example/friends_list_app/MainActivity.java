package com.example.friends_list_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Friend> friends = new ArrayList<>();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  // This sets your toolbar as the action bar

        EditText name = findViewById(R.id.name_id);
        EditText email = findViewById(R.id.email_id);
        EditText birthday = findViewById(R.id.birthday_id);

        Button btnCancel = findViewById(R.id.button_Cancel);
        Button btnCreate = findViewById(R.id.button_Create);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText(" ");
                email.setText(" ");
                birthday.setText(" ");
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(name.getText()) != " " ||
                        String.valueOf(email.getText()) != " " ||
                            String.valueOf(birthday.getText()) != " ") {

                    Friend friend = new Friend( String.valueOf(name.getText()),
                                                String.valueOf(email.getText()),
                                                String.valueOf(birthday.getText()));

                    friends.add(friend);
                    Toast.makeText(MainActivity.this, "Created friend successfully!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Forum not filled correctly!", Toast.LENGTH_LONG).show();
                }
            }
        });

        intent = new Intent(this, RecyclerActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings_Id) {
            Toast.makeText(this, "Clicked on Settings!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.friends_Id) {
            intent.putExtra("friends_list", (Serializable) friends);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}