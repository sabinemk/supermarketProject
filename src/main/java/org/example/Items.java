package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Items {

    private String itemID;
    private String itemName;
    private int amountAvailable;
    private double itemCost;
    private double itemWeight;
    private double itemPrice;
    private int quantitySold;



    public Items(String itemName, int amountAvailable, double itemCost, double itemWeight, double itemPrice, int quantitySold) {
        this.itemName = itemName;
        this.amountAvailable = amountAvailable;
        this.itemCost = itemCost;
        this.itemWeight = itemWeight;
        this.itemPrice = itemPrice;
        this.quantitySold = quantitySold;
        this.itemID= UUID.randomUUID().toString();
    }


}
