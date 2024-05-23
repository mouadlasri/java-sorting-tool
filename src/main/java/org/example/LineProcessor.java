package org.example;

import java.util.*;

public class LineProcessor extends DataProcessor<String> {
    @Override
    public List<String> readData(Scanner scanner) {
        List<String> data = new ArrayList<>();
        while (scanner.hasNextLine()) {
            data.add(scanner.nextLine());
        }

        return data;
    }

    @Override
    public String getElementType() {
        return "lines";
    }

    @Override
    public void printNaturalSortedData(List<String> data) {
        Collections.sort(data);
        System.out.print("Sorted data: ");
        for (String line : data) {
            System.out.println(line);
        }
    }

    @Override
    public void printByCountSortedData(List<String> data) {
        Map<String, Long> occurrences = new HashMap<>();

        for (String line : data) {
            occurrences.put(line, occurrences.getOrDefault(line, 0L) + 1);
        }

        List<Map.Entry<String, Long>> entryList = new ArrayList<>(occurrences.entrySet());
        entryList.sort(Map.Entry.comparingByKey());
        entryList.sort(Map.Entry.comparingByValue());

        for (Map.Entry<String, Long> entry : entryList) {
            long count = entry.getValue();
            double percentage = (count * 100.0) / data.size();
            System.out.printf("%s: %d time(s), %.0f%%%n", entry.getKey(), count, percentage);
        }
    }
}