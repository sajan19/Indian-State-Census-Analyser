package censusanalyser;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;

public class CensusAnalyser {
    //Decale file path to read data
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
            //Calculating the number of Entries
            int numOfEnteries = 0;
            while (censusCSVIterator.hasNext()) {
                numOfEnteries++;
                IndiaCensusCSV censusData = censusCSVIterator.next();
            }
            return numOfEnteries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    //Method to display Indian Census Data in Console
    public void showIndianCensusDataEntries() throws IOException {
        FileReader myFile = new FileReader(INDIA_CENSUS_CSV_FILE_PATH);
        //Create a CSVReader Object
        CSVReader reader = new CSVReader(myFile);
        //Read CSV line by line and use the string array as you want
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                //Verifying the read data here
                System.out.println(Arrays.toString(nextLine));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //Display Welcome Message
        System.out.println("Welcome to Indian States Census Analyser...");
        //Create an Object
        CensusAnalyser censusDataEntries = new CensusAnalyser();
        //Calling of Method to show Census Entries in Console
        censusDataEntries.showIndianCensusDataEntries();
    }
}
