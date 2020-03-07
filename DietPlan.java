package com.example.salauddinrubel.healthadviser;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DietPlan extends AppCompatActivity {

    private ListView listView;
    private String[] dietlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);
        dietlist = getResources().getStringArray(R.array.Calorie_Limit);

        listView = findViewById(R.id.listViewId);
        CustomAdapter adapter = new CustomAdapter(DietPlan.this, dietlist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = dietlist[position];
                Toast.makeText(DietPlan.this, value, Toast.LENGTH_LONG).show();
                if(value.equals("0-500 Calories")){
                    showMassage("Food Menu", "Breakfast: Omelet with various vegetables, fried in butter or coconut oil.\n\n" +
                            "Lunch: Grass-fed yogurt with blueberries and a handful of almonds.\n\n" +
                            "Dinner: Bun-less cheeseburger, served with vegetables and salsa sauce.");

                }
                if(value.equals("500-1000 Calories")){
                    showMassage("Food Menu", "Breakfast: Omelet with various vegetables, fried in butter or coconut oil.\n\n" +
                            "Lunch: Smoothie with coconut milk, berries, almonds and protein powder.\n\n" +
                            "Dinner: Steak and veggies.");
                }
                if(value.equals("1000-1500 Calories")){
                    showMassage("Food Menu", "Breakfast: Chicken and eggs.\n\n" +
                            "Lunch: Leftover burgers and veggies from the previous night.\n\n" +
                            "Dinner: Salmon with butter and vegetables.");
                }
                if(value.equals("1500-2000 Calories")){
                    showMassage("Food Menu", "Breakfast: Omelet with various veggies.\n\n" +
                            "Lunch: Grass-fed yogurt with berries, coconut flakes and a handful of walnuts.\n\n" +
                            "Dinner: Meatballs with vegetables.");
                }
                if(value.equals("2000-2500 Calories")){
                    showMassage("Food Menu", "Breakfast: Meat 170gm and eggs.\n\n" +
                            "Lunch: Chicken salad with some olive oil.\n\n" +
                            "Dinner: Chicken chops with vegetables.");
                }
                if(value.equals("2500-3000 Calories")){
                    showMassage("Food Menu", "Breakfast: Noodles and soup.\n\n" +
                            "Lunch: Smoothie with coconut milk, a dash of heavy cream, chocolate-flavored protein powder and berries.\n\n" +
                            "Dinner: Grilled chicken wings with some raw spinach on the side.");
                }
            }
        });





    }
    public void showMassage(String title, String Massage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Massage);
        builder.show();
    }
}
