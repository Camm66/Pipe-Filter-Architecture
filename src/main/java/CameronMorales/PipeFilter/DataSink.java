package CameronMorales.PipeFilter;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class DataSink implements Runnable{
	Pipe inputPipe;
	TreeMap<String, Integer> results;
	private String poisonPill;

	DataSink(Pipe _inputPipe){
		this.inputPipe = _inputPipe;
		this.results = new TreeMap<String, Integer>();
	}

	public void setPoisonPill(String _poisonPill) {
		this.poisonPill = _poisonPill;
	}

	public void run(){
		while(true){
			String inputData = this.inputPipe.sendOutput();
			if(inputData != this.poisonPill){
				if(inputData != null){
					transformData(inputData);
				}
			} else {
				System.out.println("Closing data sink");
				printResults();
				break;
			}
		}
	}

	private void printResults() {
		SortedSet<Entry<String, Integer>> sortedItems = entriesSortedByValues(this.results);
		Object[] items = sortedItems.toArray();
		System.out.println("Top 10 words:");
		for(int i = 1; i < 11; i++){
			System.out.println(items[items.length - i]);
		}
	}

	static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(new Comparator<Map.Entry<K,V>>() {
	            public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
	                int res = e1.getValue().compareTo(e2.getValue());
	                return res != 0 ? res : 1;
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}

	private void transformData(String inputData) {
		if(inputData != null && !inputData.isEmpty()){
			String[] data = inputData.split(" ");
			for(int i = 0; i < data.length; i++){
				if(this.results.containsKey(data[i])){
					Integer newValue = this.results.get(data[i]) + 1;
					this.results.replace(data[i], newValue);
				} else
					this.results.put(data[i], 1);
			}
		}
	}
}
