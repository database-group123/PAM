package com.example.myapplicationmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tworzenie modelu dokumentu do bazy danych. Użycie metody put dodaje kolejną parę klucz wartość do obiektu, który potem możemy dodać no bazy.
        Map<String, Object> fbModel = new HashMap<>();
        fbModel.put("nazwa", "Programowanie Aplikacji Mobilnych");

        // Dodanie dokumentu do bazy. Zmienamy w db.collection() parametr na taki jaki nas interesuje by dodac do tej kolekcji, a nastepenie w parametrze metody add dodajemy nasz wczesniej utworzony model
        db.collection("kursy")
                .add(fbModel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println(documentReference.getId());
                        Toast.makeText(MainActivity.this,"Dane do bazy" + documentReference.getId(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(e);
                    }
                });

        // Usuniecie dokumentu z kolekcji. Jaki parametr w metodzie document podajemy unikalny identyfikator dokumentu.
        db.collection("kursy").document("7Yxz9jRWfXhRSdLLtPnx")
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Usunieto dokument 7Yxz9jRWfXhRSdLLtPnx");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(e);
                    }
                });


        // Pobranie kolekcji z bazy danych. Aby otrzymać inną kolekcję zmieniamy w db.collection() parametr z "klienci" na inny
        db.collection("klienci")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                System.out.println(document.getData());
                                Toast.makeText(MainActivity.this,"Dane z bazy" + document.getData(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            System.out.println(task.getException());
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    //Exit
    public void appExitMenu(MenuItem item) {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.menu_option1:
                if(item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                    Toast.makeText(MainActivity.this,"Opcja ktoras, chyba ta pierwsza, ale kliknij raz jeszcze" + item.getTitle(), Toast.LENGTH_SHORT).show();
                }
            case R.id.menu_option2:
                if(item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                    Toast.makeText(MainActivity.this,"Jeszcze inna opcje wybrales tutaj" + item.getTitle(), Toast.LENGTH_SHORT).show();
                }
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
