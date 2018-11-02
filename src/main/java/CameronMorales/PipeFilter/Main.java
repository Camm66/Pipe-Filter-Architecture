package CameronMorales.PipeFilter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import Algorithms.PorterStemmer;

public class Main
{
    public static void main( String[] args ) throws IOException
    {
    	// Get the text file path from the user
    	//System.out.println("Enter path of the text file: ");
    	//Scanner scanner = new Scanner(System.in);
    	//String textFile = scanner.nextLine();
    	//scanner.close();
    	String textFile = "text_files/kjbible.txt";
    	// Set the stopwords file path
    	String stopwordsFile = "text_files/stopwords.txt";

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
        String poisonPill = "ssofjsoijkjfihehq";
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
        getRootForms.setAlgorithm(new PorterStemmer());

        // Begin Processing
        Thread a = new Thread(dataPump);
        Thread b = new Thread(removeNonAlpha);
        Thread c = new Thread(removeUpper);
        Thread d = new Thread(removeStopWords);
        Thread e = new Thread(getRootForms);
        Thread f = new Thread(dataSink);

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
        f.start();
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
