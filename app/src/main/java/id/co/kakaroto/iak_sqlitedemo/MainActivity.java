package id.co.kakaroto.iak_sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import id.co.kakaroto.iak_sqlitedemo.model.Person;
import id.co.kakaroto.iak_sqlitedemo.utility.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseHandler databaseHandler = new DatabaseHandler(this);
/*        databaseHandler.addPerson(new Person("Budi", "Mancing"));
        databaseHandler.addPerson(new Person("Andi", "Membaca"));
        databaseHandler.addPerson(new Person("Yudi", "Olahraga"));
        databaseHandler.addPerson(new Person("Rendi", "Makan"));
        databaseHandler.addPerson(new Person("Hadi", "Tidur"));*/

        for (Person person : databaseHandler.getAllPerson()) {
//            databaseHandler.deletePerson(person);
            Log.d(TAG, "onCreate: " + person.toString());
        }
    }
}
