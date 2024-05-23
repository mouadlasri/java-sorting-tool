package org.example;

import java.util.Scanner;
import java.util.List;

public abstract class DataProcessor<T> {

    public abstract List<T> readData(Scanner scanner);

    public abstract String getElementType();

    public abstract void printNaturalSortedData(List<T> data);

    public abstract void printByCountSortedData(List<T> data);

    // main processing method that uses the above abstract methods
    public void process(Scanner scanner, String sortingType) {
        List<T> data = readData(scanner);

        // if data is empty, print a message and return
        if (data.isEmpty()) {
            System.out.printf("Total %s: 0.%n", getElementType());
            return;
        }

        System.out.printf("Total %s: %d.%n", getElementType(), data.size());

        if ("byCount".equals(sortingType)) {
            printByCountSortedData(data);
        } else {
            printNaturalSortedData(data);
        }
    }
}