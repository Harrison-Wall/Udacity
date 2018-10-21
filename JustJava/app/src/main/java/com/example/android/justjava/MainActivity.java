package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
        EditText nField = (EditText) findViewById(R.id.name_field);
        String userName = nField.getText().toString();

        CheckBox whipped = (CheckBox) findViewById(R.id.whipped_box);
        boolean wantsWhipped = whipped.isChecked();

        CheckBox choco = (CheckBox) findViewById(R.id.choco_box);
        boolean wantsChoco = choco.isChecked();

        int price = calculatePrice(wantsWhipped, wantsChoco);

        String message = createOrderSummary(price, wantsWhipped, wantsChoco, userName);

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
        if(quantity <= 100)
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
     * Calculates the price of the order based on quantity and topping.
     * @param wantsWhippedCream is whether or not the user wants whipped cream topping
     * @param wantsChocolate is whether or not the user wants chocolate topping
     * @return total price
     */
    private int calculatePrice(boolean wantsWhippedCream, boolean wantsChocolate)
    {
        int price = 5;

        if(wantsChocolate) // Chocolate is $2 extra
            price += 2;
        if(wantsWhippedCream) // Whipped Cream is $1 extra
            price++;

        return quantity * price;
    }

    /**
     * Builds a string of the order's information
     * @param price the cost of a cup of coffee
     * @param wantsWhippedCream is whether or not the user wants whipped cream topping
     * @param wantsChocolate is whether or not the user wants chocolate topping
     * @return order details
     */
    private String createOrderSummary(int price, boolean wantsWhippedCream, boolean wantsChocolate, String name)
    {
        String summary;

        if( quantity > 0 )
        {
            summary = "Name: " + name
                    + "\nAdd Whipped cream? "+ wantsWhippedCream
                    + "\nAdd Chocolate? " + wantsChocolate
                    + "\nQuantity: " + quantity
                    + "\nTotal: $" + price
                    + "\nThank You!";
        }
        else
            summary = "Please have a cup.";

        return summary;
    }

}