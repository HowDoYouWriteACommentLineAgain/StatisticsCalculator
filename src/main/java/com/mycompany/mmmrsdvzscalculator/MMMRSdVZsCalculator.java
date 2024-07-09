/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mmmrsdvzscalculator;
import java.util.Scanner;
//import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class MMMRSdVZsCalculator {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        DataSet dataset1 = new DataSet();
        
        System.out.println("Welcome user.");        
        System.out.println("Please input the raw data for the set.\nWhen you wish to stop please enter an Exclamation mark [!]");

        try {
            dataset1.prompt(userInput);
            dataset1.printout();
            
            System.out.println("Your Data set's mean is: " 
                    + String.format("%.2f",dataset1.getMean()));
            
            System.out.println("Your Data set's median is: " 
                    + String.format("%.2f",dataset1.getMedian()));
            
            dataset1.printMode();
            System.out.println("Your Data set's range is: " + dataset1.getRange());
            
            System.out.println("Your Data set's standard deviation is: " 
                    + String.format("%.2f", dataset1.getStandardDeviation()));
            
            System.out.println("Your Data set's variance is: " 
                    + String.format("%.2f", dataset1.getVariance()));
            
            System.out.println("Please input a number to calculate for the Z Score.");
            
            System.out.println("Your Z-Score is: " 
                    + String.format("%.2f", dataset1.promptAndPrintZScore(userInput)));
            

        } catch (NumberFormatException e) {
            System.out.println("Input Invalid. Please Rerun and try again.");
        }        
    }
}
