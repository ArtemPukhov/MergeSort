import java.io.*;
import java.util.*;

public class MergeSorter {

    public static class DataElement implements Comparable<DataElement> {
        private final String value;

        public DataElement(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public int compareTo(DataElement other) {
            return this.value.compareTo(other.value);
        }
    }
    public static List<DataElement> readInputFile(String inputFile) throws IOException {
        List<DataElement> dataElements = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dataElements.add(new DataElement(line));
            }
        }

        return dataElements;
    }

    public static void writeOutputFile(String outputFile, List<DataElement> sortedData) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (DataElement element : sortedData) {
                writer.write(element.getValue());
                writer.newLine();
            }
        }
    }

    static List<DataElement> mergeSort(List<DataElement> dataElements) {
        if (dataElements.size() <= 1) {
            return dataElements;
        }

        int mid = dataElements.size() / 2;
        List<DataElement> left = dataElements.subList(0, mid);
        List<DataElement> right = dataElements.subList(mid, dataElements.size());

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static List<DataElement> merge(List<DataElement> left, List<DataElement> right) {
        List<DataElement> mergedList = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            DataElement leftElement = left.get(leftIndex);
            DataElement rightElement = right.get(rightIndex);

            if (leftElement.compareTo(rightElement) <= 0) {
                mergedList.add(leftElement);
                leftIndex++;
            } else {
                mergedList.add(rightElement);
                rightIndex++;
            }
        }

        mergedList.addAll(left.subList(leftIndex, left.size()));
        mergedList.addAll(right.subList(rightIndex, right.size()));

        return mergedList;
    }

    static List<Integer> mergeSortIntegers(List<Integer> data) {
        if (data.size() <= 1) {
            return data;
        }

        int mid = data.size() / 2;
        List<Integer> left = data.subList(0, mid);
        List<Integer> right = data.subList(mid, data.size());

        left = mergeSortIntegers(left);
        right = mergeSortIntegers(right);

        return mergeIntegers(left, right);
    }

    private static List<Integer> mergeIntegers(List<Integer> left, List<Integer> right) {
        List<Integer> mergedList = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            Integer leftElement = left.get(leftIndex);
            Integer rightElement = right.get(rightIndex);

            if (leftElement.compareTo(rightElement) <= 0) {
                mergedList.add(leftElement);
                leftIndex++;
            } else {
                mergedList.add(rightElement);
                rightIndex++;
            }
        }

        mergedList.addAll(left.subList(leftIndex, left.size()));
        mergedList.addAll(right.subList(rightIndex, right.size()));

        return mergedList;
    }
}
