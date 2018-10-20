package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Global variables for the number of Coffees and their price
     */
    int quantity = 2;
    int price = 5;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)
    {
        String priceMessage = "Total: $" + (quantity * price);
        priceMessage += "\nThank You!";

        if( quantity > 0 )
            displayMessage(priceMessage);
        else
            displayMessage("Please have a cup.");
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number)
    {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method increments the quantity when clicking the - button.
     */
    public void increment(View view)
    {
        quantity++;
        display(quantity);
    }

    /**
     * This method decrements the quantity when clicking the - button.
     */
    public void decrement(View view)
    {
        if( quantity > 0 )
            quantity--;

        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}