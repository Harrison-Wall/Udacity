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
    /**
     * Global variables for the number of Coffees and their price
     */
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)
    {
        int price = calculatePrice();

        String message = createOrderSummary(price);

        displayMessage(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     * @param numCoffee is the number of coffees ordered
     */
    private void displayQuantity(int numCoffee)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numCoffee);
    }

    /**
     * This method increments the quantity when clicking the plus button.
     */
    public void increment(View view)
    {
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method decrements the quantity when clicking the minus button.
     */
    public void decrement(View view)
    {
        if( quantity > 0 )
            quantity--;

        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     * @param  message is what the TextView should say.
     */
    private void displayMessage(String message)
    {
        TextView orderSumTextView = (TextView) findViewById(R.id.order_sum_text_view);
        orderSumTextView.setText(message);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     * @return total price
     */
    private int calculatePrice()
    {
        return quantity * 5;
    }

    /**
     * Builds a string of the order's information
     * @param price the cost of a cup of coffee
     * @return order details
     */
    private String createOrderSummary(int price)
    {
        String summary;

        if( quantity > 0 )
        {
            summary = "Name: FirstName LastName\nQuantity: " + quantity
                    + "\nTotal: $" + price
                    + "\nThank You!";
        }
        else
            summary = "Please have a cup.";

        return summary;
    }
}