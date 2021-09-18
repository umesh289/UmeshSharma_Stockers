package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class DriverClass {

    int numberOfCompanies;
    ArrayList<Double> stockPrices = new ArrayList<Double>();
    ArrayList<Boolean> stockStatus = new ArrayList<Boolean>();

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);

        DriverClass driver = new DriverClass();
        driver.enterDetails(scanner);

        while (true) {
            int option = driver.presentUserOptions(scanner);
            driver.performOperation(option);
        }
    }

    private void enterDetails(Scanner scanner){
        System.out.println("Enter the no of companies");
        numberOfCompanies = scanner.nextInt();

        for (int i=1; i <= numberOfCompanies; i++){
            System.out.println("Enter current stock price of the company " + i);
            double price  = scanner.nextDouble();

            System.out.println("Whether company's stock price rose today compare to yesterday?");
            boolean rose  = scanner.nextBoolean();

            stockPrices.add(price);
            stockStatus.add(rose);
        }
    }

    private int presentUserOptions(Scanner scanner){
        System.out.println("Enter the operation that you want to perform\n" +
        "1. Display the companies stock prices in ascending order\n" +
        "2. Display the companies stock prices in descending order\n" +
        "3. Display the total no of companies for which stock prices rose today\n" +
        "4. Display the total no of companies for which stock prices declined today\n" +
        "5. Search a specific stock price\n" +
        "6. press 0 to exit");

        int option  = scanner.nextInt();

        return option;
    }

    private void performOperation(int option) {
        switch (option) {
            case 0:
            case 6:
                exit(0);
                break;
            case 1:
                ascendingOrder();
                break;
            case 2:
                descendingOrder();
                break;
            case 3:
                risingStocks();
                break;
            case 4:
                declinedStocks();
                break;
            case 5:
                getStockDetails();
                break;
            default:
                exit(1);
        }
    }

    private void ascendingOrder(){
        System.out.println("Stock prices in ascending order are:");

        Double[] prices = new Double[this.stockPrices.size()];
        prices = this.stockPrices.toArray(prices);

        MergeSort sorter = new MergeSort();
        sorter.sort(prices, 0, prices.length - 1);

        sorter.printArray(prices);
    }

    private void descendingOrder(){
        System.out.println("Stock prices in descending order are:");

        Double[] prices = new Double[this.stockPrices.size()];
        prices = this.stockPrices.toArray(prices);

        MergeSort sorter = new MergeSort();
        sorter.sort(prices, 0, prices.length - 1);

        sorter.printArrayReverse(prices);
    }

    private void risingStocks(){
        System.out.println("Total number of companies whose stock rose today:");

        int count = 0;
        for (int i=0; i < stockStatus.size(); i++){
            if (stockStatus.get(i) == true) {
                count ++;
            }
        }

        System.out.println(count);

    }

    private void declinedStocks(){
        System.out.println("Total number of companies whose stock declined today:");

        int count = 0;
        for (int i=0; i < stockStatus.size(); i++){
            if (stockStatus.get(i) == false) {
                count ++;
            }
        }

        System.out.println(count);
    }

    private void getStockDetails(){
        System.out.println("Enter key value:");
        Scanner scanner = new Scanner(System.in);
        double value = scanner.nextDouble();

        Double[] prices = new Double[this.stockPrices.size()];
        prices = this.stockPrices.toArray(prices);

        MergeSort sorter = new MergeSort();
        sorter.sort(prices, 0, prices.length - 1);

        BinarySearch binarySearch = new BinarySearch();

        int ret = binarySearch.binarySearch(prices, 0, prices.length - 1, value);

        if (ret == -1){
            System.out.println("Value not found");
        } else {
            System.out.println("Stock of value " + value + " is present");
        }
    }
}
