package CameronMorales.PipeFilter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import Algorithms.IPorterStemmer;
import Algorithms.PorterStemmer;

public class Main
{
    public static void main( String[] args ) throws IOException
    {
    	// Get the text file path from the user
    	System.out.println("Enter path of the text file: ");
    	Scanner scanner = new Scanner(System.in);
    	String textFile = scanner.nextLine();

    	//Get the language selection
    	System.out.println("Select the language to process: ");
    	String options = "\nAvailable Languages: "
				+ "\n   1            - English"
				+ "\n   2            - French"
				+ "\n   3            - German"
				+ "\n   Enter        - Default(English).\n\n";
		System.out.println(options);
    	String languageChoice = scanner.nextLine();
    	scanner.close();

    	// Configure the appropriate stopwords.txt file and PorterStemmer Algorithm
    	IPorterStemmer porterStemmer;
    	String stopwordsFile;
    	if(languageChoice.equals("1")){
    		stopwordsFile = "text_files/stopwords.txt";
        	porterStemmer= new PorterStemmer();
    	}
    	else if(languageChoice.equals("2")){
    		stopwordsFile = "text_files/stopwords_French.txt";
    		porterStemmer= new FrenchPorterStemmer();
    	}
    	else if(languageChoice.equals("2")){
    		stopwordsFile = "text_files/stopwords_German.txt";
    		porterStemmer= new GermanPorterStemmer();

    	}
    	else{
    		stopwordsFile = "text_files/stopwords.txt";
        	porterStemmer= new PorterStemmer();
    	}



    	System.out.println();
        System.out.println("Running Pipe-Filter Configuration!");

        // Initialize Pipes
        Pipe pipeA = new Pipe();
        Pipe pipeB = new Pipe();
        Pipe pipeC = new Pipe();
        Pipe pipeD = new Pipe();
        Pipe pipeE = new Pipe();

        // Initialize Filters
        FilterRemoveNonAlpha removeNonAlpha = new FilterRemoveNonAlpha(pipeA, pipeB);
        FilterRemoveUpperCase removeUpper = new FilterRemoveUpperCase(pipeB, pipeC);
        FilterRemoveStopwords removeStopWords = new FilterRemoveStopwords(pipeC, pipeD);
        FilterRootForms getRootForms = new FilterRootForms(pipeD, pipeE);

        // Initialize Endpoints
        DataPump dataPump = new DataPump(pipeA);
        DataSink dataSink = new DataSink(pipeE);

        // Set poison pill
        String poisonPill = "ssofjsoijkjfihehqhih4928hh2h304h0idhsh4pl392hcn567cxxz013plfnam5870das";
        removeStopWords.setPoisonPill(poisonPill);
        removeNonAlpha.setPoisonPill(poisonPill);
        removeUpper.setPoisonPill(poisonPill);
        getRootForms.setPoisonPill(poisonPill);
        dataPump.setPoisonPill(poisonPill);
        dataSink.setPoisonPill(poisonPill);

        // Feed Stop Words to  FilterRemoveStopwords
        ArrayList<String> stopwords = getStopWords(stopwordsFile);
        removeStopWords.setStopwords(stopwords);

        // Feed the text file being processed to the DataPump
        dataPump.setFilename(textFile);

        // Set the PorterStemmer algorithm
        getRootForms.setAlgorithm(porterStemmer);

        // Begin Processing
        dataPump.run();
        removeNonAlpha.run();
        removeUpper.run();
        removeStopWords.run();
        getRootForms.run();
        dataSink.run();
    }

	private static ArrayList<String> getStopWords(String _stopwords) throws IOException {
		ArrayList<String> stopwords = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(_stopwords)));
		try {
	    	String line;
	    	while ((line = br.readLine()) != null) {
	    		stopwords.add(line);
	    	}
		} finally {
			br.close();
		}
		return stopwords;
	}
}
