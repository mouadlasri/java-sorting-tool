package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.io.PrintStream;

public class Main {
    public static void main(final String[] args) {
        String sortingType = "natural";
        String dataType = "word"; // default data type
        String inputFile = null;
        String outputFile = null;

        boolean sortingTypeDefined = false;
        boolean dataTypeDefined = false;

        Set<String> validArguments = new HashSet<>(Arrays.asList("-sortingType", "-dataType"));

        // parse command-line arguments
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-sortingType":
                    sortingTypeDefined = true;
                    // check if the next argument is not another flag and is present
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        sortingType = args[i + 1];
                        i++;
                    } else {
                        System.out.println("No sorting type defined!");
                        return;
                    }
                    break;
                case "-dataType":
                    dataTypeDefined = true;
                    // check if the next argument is not another flag and is present
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        dataType = args[i + 1];
                        i++;
                    } else {
                        System.out.println("No data type defined!");
                        return;
                    }
                    break;
                case "-inputFile":
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        inputFile = args[i + 1];
                        i++;
                    } else {
                        System.out.println("No input file defined!");
                        return;
                    }
                    break;
                case "-outputFile":
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        outputFile = args[i + 1];
                        i++;
                    } else {
                        System.out.println("No output file defined!");
                        return;
                    }
                    break;
                default:
                    if (!args[i].startsWith("-") || !validArguments.contains(args[i])) {
                        System.out.printf("\"%s\" is not a valid parameter. It will be skipped.%n", args[i]);
                    }
                    break;

            }
        }

        // prepare input and output streams
        Scanner scanner = null;
        PrintStream out = System.out;
        boolean shouldCloseScanner = false;
        boolean shouldClosePrintStream = false;

        try {
            if (inputFile != null) {
                scanner = new Scanner(new File(inputFile));
                shouldCloseScanner = true;
            } else {
                scanner = new Scanner(System.in);
            }

            if (outputFile != null) {
                out = new PrintStream(new FileOutputStream(outputFile));
                shouldClosePrintStream = true;
            }

            // redirect System.out to the specified output file if necessary
            System.setOut(out);

            // create the appropriate DataProcessor subclass instance based on the data type
            DataProcessor<?> processor;
            switch (dataType) {
                case "long":
                    processor = new LongProcessor();
                    break;
                case "line":
                    processor = new LineProcessor();
                    break;
                case "word":
                default:
                    processor = new WordProcessor();
                    break;
            }

            // process the input data
            processor.process(scanner, sortingType);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } finally {
            if (shouldCloseScanner && scanner != null) {
                scanner.close();
            }
            if (shouldClosePrintStream && out != null) {
                out.close();
            }
        }
    }
}