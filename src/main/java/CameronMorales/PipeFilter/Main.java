package CameronMorales.PipeFilter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Running Pipe-Filter Configuration!");

        // Initialize Pipes
        Pipe pipeA = new Pipe();
        Pipe pipeB = new Pipe();
        Pipe pipeC = new Pipe();
        Pipe pipeD = new Pipe();
        Pipe pipeE = new Pipe();

        // Initialize Filters
        FilterRemoveNonAlpha removeNonAlpha = new FilterRemoveNonAlpha(pipeA, pipeB);
        FilterRemoveLowerCase removeUpper = new FilterRemoveLowerCase(pipeB, pipeC);
        FilterRemoveStopwords removeStopWords = new FilterRemoveStopwords(pipeC, pipeD);
        FilterRootForms getRootForms = new FilterRootForms(pipeD, pipeE);

        // Initialize Endpoints
        DataPump dataPump = new DataPump(pipeA);
        DataSink dataSink = new DataSink(pipeE);

        // Set poison pill
        String poisonPill = "ssofjsoijkjfihehqhih4928hh2h304h0idhsh4pl3";
        removeStopWords.setPoisonPill(poisonPill);
        removeNonAlpha.setPoisonPill(poisonPill);
        removeUpper.setPoisonPill(poisonPill);
        getRootForms.setPoisonPill(poisonPill);
        dataPump.setPoisonPill(poisonPill);
        dataSink.setPoisonPill(poisonPill);

        // Set external text
        ArrayList<String> stopwords = getStopWords();
        removeStopWords.setStopwords(stopwords);
        dataPump.setFilename("text_files/kjbible.txt");

        // Begin Processing
        dataPump.run();
        removeNonAlpha.run();
        removeUpper.run();
        removeStopWords.run();
        getRootForms.run();
        dataSink.run();
    }

	private static ArrayList<String> getStopWords() throws IOException {
		ArrayList<String> stopwords = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("text_files/stopwords.txt")));
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
