/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mmmrsdvzscalculator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
/**
 *
 * @author Admin
 */
public class DataSet extends ArrayList<Double>{
    public void prompt(Scanner s){
        int lastInputChar;        
        do{
            String input = s.next();
            lastInputChar = input.charAt(0);//stores last input as char.
            
            if (lastInputChar == '!') {//guard clause; breaks if [.] invoked
                break;
            
            } else {//passes
                double lastNum = Double.parseDouble(input);//

                this.add(lastNum);//Assigns value to arraylist.
            }
        }while(lastInputChar != '!');
        
        Collections.sort(this, Collections.reverseOrder());
        //Sorts the input from greatest to least.
    }
    
    public void printout(){
        System.out.println("--------------------------------------------------");

//        System.out.println("#  X <--> X-M <--> (X-M)^2");
        String headerLines = "%s  %s    \t %s \t %s";
        String dataLines   = "%s % -12.2f % -12.2f % -12.2f";
        
        String header = String.format(headerLines,"#","X","X-M","(X-M)^2");
        System.out.println(header+"\n");
        
        int index = 1;
        for (Double i : this) {
            
            String position = index+"";
            double xm = (i-this.getMean());
            double xm2 = Math.pow(i - this.getMean(), 2);
            
            String line = String.format(dataLines,position,i,xm,xm2);

            System.out.println(line);
            index++;            
        }
        System.out.println("--------------------------------------------------\n\n");
    }
    
    public double getMean(){
        double sum = 0;
        for (Double i : this) {
            sum += i;//adds up all the input
        }
        
        sum /= this.size();//divides by the size
        return sum;
    }
    
    public double getMedian(){
        double medianPlace;
        int medianPlaceInt;
        medianPlace = (this.size()+1); //(n+1)/2
        medianPlace /= 2; 

        if((medianPlace % 1)== 0){//If (n+1)/2 returns a round integer.
            medianPlaceInt = (int)medianPlace;//result it the location of the median
            return this.get(medianPlaceInt);
            
        }else{//else it is between two input.
            medianPlaceInt = (int) medianPlace; //eg. 4.5 => 4
            double median1 = this.get(medianPlaceInt); // get the 4rth data
            double median2 = this.get(medianPlaceInt - 1);//get the (4-1)th data. -1 since list was reversed.
            double sum = median1+median2;//get the sum between the two input.
            return sum/2;//and then divide by 2 to get average.
            
        }                
    }
    
    public void printMode(){//Prints the mode if there is a unique one.
        int maxCount = 1;//highest count. Default 1.
        double ValueOfMode = 1;//The value of the mode. Default 1.
        int numberOfModes = 1;
        boolean MoreThanOnceOccured = false;
        ArrayList<Double> locations = new ArrayList<Double>();
        
        for(double i: this){//check this data.
            int count = 0;//reset count
            
            for(double j: this){//compare that data to the others.
                if (Objects.equals(i, j))
                    count++;//Add to count for each match.
            }
            
            if(count>maxCount){//If count was higher than before then rewrite max count with current count.
                MoreThanOnceOccured = true;
                locations.clear();//since data repeated more than the others. Delete previously saved data.
                
                locations.add(i);//saves the data.
                maxCount=count;
                ValueOfMode=i;//single mode.
                numberOfModes=1;
            }
            
            if(count==maxCount){
                locations.add(i);//saves the data.
                maxCount=count;
                ValueOfMode=i;//single mode detected
                numberOfModes++;
                
            }
        }
        if(MoreThanOnceOccured) {//gaurd clause for if mode/s exist/s.
            if(numberOfModes>2){//gaurd clause for more than one mode.
                System.out.print("Your Data set's modes were:");
                int printUnique=maxCount;
                for(double i: locations){
                    
                    if(printUnique==1){
                        System.out.print(" "+i+",");
                        printUnique=maxCount;
                    }else printUnique--;
                    
                }
                System.out.println("\b.");


            }else//Only one mode
                System.out.println("Your Data set's mode is: "+ValueOfMode);
        } else {//No mode/all data were unique.
            System.out.println("All items occur exactly once.");
            //If no items reoccur then there is no mode and print the following.
        }
        
    }
    
    public double getRange(){
        return this.get(0)-this.get(this.size()-1);//high-low = range.
    }
    
    public double getStandardDeviation(){
        double SumOfDifferenceSquared = 0;
        double standardDeviation;
        for (Double i : this) {
            double differenceToMeanSquared = Math.pow(i-this.getMean(), 2);//Sum of (input-mean)^2.
            SumOfDifferenceSquared += differenceToMeanSquared;
        }
        standardDeviation = Math.sqrt(SumOfDifferenceSquared/this.size());//Square root the result.
        return standardDeviation;
    }
    
    public double getVariance() {
        double SumOfDifferenceSquared = 0;
        double variance;
        for (Double i : this) {
            double differenceToMeanSquared = Math.pow(i - this.getMean(), 2);//Sum of (input-mean)^2.
            SumOfDifferenceSquared += differenceToMeanSquared;
        }
        variance = SumOfDifferenceSquared / this.size();
        return variance;
    }
    
    public double promptAndPrintZScore(Scanner s){
        do{
            double input = s.nextDouble();//gets next score to compare for Z score.
            if(input<=this.get(0)&&this.get(this.size()-1)<=input)//Checks if input was in range.
                return (input - this.getMean())/this.getStandardDeviation();//(input-mean)/SD
            
            System.out.println("Input was out of range please input between "+this.get(0)+" and "+this.get(this.size()-1));
        }while(true);//Will ensure that loop cannot be broken until valid and input was given.
    }
}
