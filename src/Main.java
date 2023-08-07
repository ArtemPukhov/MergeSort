import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Main <sorting_mode> <data_type> <output_file> <input_files>");
            System.exit(1);
        }

        String sortingMode = args[0]; // Режим сортировки: "-a" или "-d"
        String dataType = args[1]; // Тип данных: "-s" или "-i"
        String outputFile = args[2]; // Имя выходного файла

        List<String> inputFiles = new ArrayList<>();
        for (int i = 3; i < args.length; i++) {
            inputFiles.add(args[i]);
        }

        try {
            List<MergeSorter.DataElement> dataElements = new ArrayList<>();

            for (String inputFile : inputFiles) {
                List<MergeSorter.DataElement> fileData = MergeSorter.readInputFile(inputFile);
                dataElements.addAll(fileData);
            }

            if (dataType.equals("-s")) {
                List<MergeSorter.DataElement> sortedData = MergeSorter.mergeSort(dataElements);
                if (sortingMode.equals("-d")) {
                    Collections.reverse(sortedData);
                }
                MergeSorter.writeOutputFile(outputFile, sortedData);
            } else if (dataType.equals("-i")) {
                List<Integer> intData = new ArrayList<>();
                for (MergeSorter.DataElement element : dataElements) {
                    intData.add(Integer.parseInt(element.getValue()));
                }

                List<Integer> sortedIntegers = MergeSorter.mergeSortIntegers(intData);

                if (sortingMode.equals("-d")) {
                    Collections.reverse(sortedIntegers);
                }

                List<MergeSorter.DataElement> sortedData = new ArrayList<>();
                for (Integer value : sortedIntegers) {
                    sortedData.add(new MergeSorter.DataElement(value.toString()));
                }

                MergeSorter.writeOutputFile(outputFile, sortedData);
            } else {
                System.out.println("Invalid data type. Use '-s' for strings or '-i' for integers.");
            }
        } catch (IOException e) {
            System.out.println("Error while processing files: " + e.getMessage());
        }
    }
}
