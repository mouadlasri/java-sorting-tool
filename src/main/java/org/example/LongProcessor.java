package org.example;

import java.util.*;

public class LongProcessor extends DataProcessor<Long>{
    @Override
    public List<Long> readData(Scanner scanner) {
        /// read long integers from the scammer
        List<Long> numbers = new ArrayList<>();
        while (scanner.hasNext()) {
            if (scanner.hasNextLong()) {
                // add valid long numbers to the list
                numbers.add(scanner.nextLong());
            } else {
                String invalidInput = scanner.next();
                System.out.printf("\"%s\" is not a long. It will be skipped.%n", invalidInput);
            }
        }

        return numbers;
    }

    @Override
    public String getElementType() {
        return "numbers";
    }

    @Override
    public void printNaturalSortedData(List<Long> data) {
        Collections.sort(data);

        System.out.print("Sorted data: ");

        for (Long number : data) {
            System.out.print(number + " ");
        }

        System.out.println();
    }

    @Override
    public void printByCountSortedData(List<Long> data) {
        Map<Long, Long> occurrences = new HashMap<>();

        for (Long number : data) {
            occurrences.put(number, occurrences.getOrDefault(number, 0L) + 1);
        }

        List<Map.Entry<Long, Long>> entryList = new ArrayList<>(occurrences.entrySet());
        // sort by count and then by value
        entryList.sort(Map.Entry.comparingByKey());
        entryList.sort(Map.Entry.comparingByValue());

        for (Map.Entry<Long, Long> entry : entryList) {
            long count = entry.getValue();
            double percentage = (count * 100.0) / data.size();
            System.out.printf("%d: %d time(s), %.0f%%%n", entry.getKey(), count, percentage);
        }
    }
}