/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjavaone;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;


    String message1, message2, message3, message4, message5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        String name;
        CheckBox checkBox1 = findViewById(R.id.check_box1);
        boolean check1 = checkBox1.isChecked();

        CheckBox checkBox2 = findViewById(R.id.check_box2);
        boolean check2 = checkBox2.isChecked();


        EditText editName = (EditText) findViewById(R.id.edit_text_view);
        name = editName.getText().toString();


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:letstalk@chaayos.com"));// only email apps should handle this

        intent.putExtra(intent.EXTRA_EMAIL, "bhanupratap9013@gmail.com");
        intent.putExtra(intent.EXTRA_TEXT, createOrderSummary(check1, check2, name));
        intent.putExtra(intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, name));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public String createOrderSummary(boolean check1, boolean check2, String name) {


        String message = getString(R.string.order_summary_name, name) + "\n";
        message += getString(R.string.order_summary_whipped_cream, check1) + "\n";
        message += getString(R.string.order_summary_chocolate, check2) + "\n";
        message += getString(R.string.order_summary_quantity, quantity) + "\n";
        message += getString(R.string.order_summary_price, calculatePrice(check1, check2)) + "\n";
        message += getString(R.string.thank_you);

        return message;
    }


    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayQuantity(int num) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + num);
    }

    public void increment(View view) {
        if (quantity == 100) {
            quantity = 100;
            Toast.makeText(this, "100 cups Max. Per person", Toast.LENGTH_SHORT).show();

        } else
            quantity = quantity + 1;


        displayQuantity(quantity);
    }


    /**
     * Calculates the price of the order.
     */
    private String calculatePrice(boolean check1, boolean check2) {
        int pricePerCup = 10;

        if (check1 == true)
            pricePerCup += 1;

        if (check2 == true)
            pricePerCup += 2;

        int price = pricePerCup * quantity;

        return "$" + price;
    }


    public void decrement(View view) {

        if (quantity == 1) {
            quantity = 1;
            Toast.makeText(this, "Minimum 1 cup perosn ", Toast.LENGTH_SHORT).show();

        } else
            quantity = quantity - 1;

        displayQuantity(quantity);
    }


}


