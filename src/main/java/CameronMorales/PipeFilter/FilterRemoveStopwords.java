package CameronMorales.PipeFilter;

import java.util.ArrayList;

public class FilterRemoveStopwords extends Filter {
	private ArrayList<String> stopwords;

	FilterRemoveStopwords(Pipe _inputPipe, Pipe _outputPipe) {
		super(_inputPipe, _outputPipe);
	}

	public void setStopwords(ArrayList<String> _stopwords){
		this.stopwords = _stopwords;
	}

	public void run(){
		System.out.println("Filter-RemoveStopwords Begins: " + System.currentTimeMillis());
		while(true){
			String inputData = this.inputPipe.sendOutput();
			if(this.poisonPill.equals(inputData))
				break;
			else if(inputData != "" && inputData != null){
					String outputData = transformData(inputData);
					this.outputPipe.getInput(outputData);
			}
			else
				continue;
		}
		this.outputPipe.getInput(this.poisonPill);
		System.out.println("Filter-RemoveStopwords Ends: " + System.currentTimeMillis());
	}

	private String transformData(String inputData) {
		String transformedData = "";
		String[] data = inputData.split(" ");
		for(int i = 0; i < data.length; i++){
			if(!this.stopwords.contains(data[i])){
				transformedData += (data[i] + " ");
			}
		}
		return transformedData;
	}
}
