package ru.nice_solutions.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    int quantity = 2;
    boolean hasCream = false;
    boolean hasChocolate = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        /** TextView quantityTextView = (TextView) findViewById(
         R.id.quantity_text_view);
         int quantity = Integer.parseInt(quantityTextView.getText().toString()); */
        display(quantity);
        displayOrderSummary();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayOrderSummary() {
      /*TextView priceTextView = (TextView) findViewById(
                R.id.price_text_view);

        priceTextView.setText("Total: "number * 10 + " RUB");*/
        hasCream = ((CheckBox) findViewById(R.id.cream_checkbox)).isChecked();
        hasChocolate = ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked();
        String clientName = (((TextView) findViewById(R.id.name_EditText)).getText()).toString();
        int number = calculatePrice(hasChocolate, hasCream);
        String name = getResources().getString(R.string.client_name);
        String ADDWC = getResources().getString(R.string.add_cream);
        String ADDCH = getResources().getString(R.string.add_chocolate);
        String quant = getResources().getString(R.string.quantity);
        String total = getResources().getString(R.string.total);
        String thanks = getResources().getString(R.string.thanks);

        String OrderSumaryMessage = name + ": " + clientName
                + "\n" + ADDWC + ":  " + hasCream
                + "\n" + ADDCH + ":  " + hasChocolate
                + "\n" + quant + ": " + quantity
                + "\n" + total + ": " + number
                + "\n" + thanks + "!";

        displayMessage(OrderSumaryMessage);
        //composeEmail("dayroned@gmail.com","test", OrderSumaryMessage);
    }

    public void increment(View view) {
        /**      TextView quantityTextView = (TextView) findViewById(
         R.id.quantity_text_view);
         int quantity = Integer.parseInt(quantityTextView.getText().toString()); */

        if (quantity < 100) {
            ++quantity;
        } else {
            Toast.makeText(this, "No puedes encargar más de 100 tazas de café", 300).show();
        }
        display(quantity);
    }

    public void decrement(View view) {
        /**     TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
         int quantity = Integer.parseInt(quantityTextView.getText().toString()); */
        if (quantity > 1) {
            --quantity;
        } else {
            Toast.makeText(this, "No puedes encargar menos de 1 taza de café", 300).show();
        }
        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

    private int calculatePrice(boolean phasChocolate, boolean phasCream) {
        int price = 5;
        if (phasChocolate) {
            price = price + 2;
        }
        if (phasCream) {
            price++;
        }
        return quantity * price;

    }

    public void composeEmail(String addresses, String subject, String emailtext) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailtext);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://ru.nice_solutions.justjava/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://ru.nice_solutions.justjava/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}