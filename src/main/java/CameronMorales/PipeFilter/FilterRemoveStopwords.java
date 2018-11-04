package CameronMorales.PipeFilter;

import java.util.HashSet;

public class FilterRemoveStopwords extends Filter {
	private HashSet<String> stopwords;

	FilterRemoveStopwords(Pipe _inputPipe, Pipe _outputPipe) {
		super(_inputPipe, _outputPipe);
	}

	public void setStopwords(HashSet<String> _stopwords){
		this.stopwords = _stopwords;
	}

	public void run(){
		while(true){
			String inputData = this.inputPipe.sendOutput();
			if(inputData == this.poisonPill){
				this.outputPipe.getInput(this.poisonPill);
				break;
			} else if(inputData != null && !inputData.equals("")) {
				transformData(inputData);
			}
		}
	}

	private void transformData(String inputData) {
		String transformedData = "";
		String[] data = inputData.split(" ");
		for(int i = 0; i < data.length; i++){
			if(!this.stopwords.contains(data[i])){
				transformedData += (data[i] + " ");
			}
		}
		 this.outputPipe.getInput(transformedData);
	}
}
