package CameronMorales.PipeFilter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Algorithms.PorterStemmer;

public class Main
{
    public static void main( String[] args ) throws IOException
    {

        System.out.println("Running Pipe-Filter Configuration!");

        String stopwordsFile = "text_files/stopwords.txt";
        String textFile = "text_files/kjbible.txt";

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
        getRootForms.setAlgorithm(new PorterStemmer());

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
