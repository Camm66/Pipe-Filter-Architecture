package CameronMorales.PipeFilter;

import Algorithms.IPorterStemmer;

public class FilterRootForms extends Filter {

	private IPorterStemmer stemmerAlgorithm;

	FilterRootForms(Pipe _inputPipe, Pipe _outputPipe) {
		super(_inputPipe, _outputPipe);
	}

	public void setAlgorithm(IPorterStemmer _algorithm){
		this.stemmerAlgorithm = _algorithm;
	}

	public void run(){
		while(true){
			String inputData = this.inputPipe.sendOutput();
			if(inputData == this.poisonPill){
				this.outputPipe.getInput(this.poisonPill);
				break;
			} else if(inputData != null && inputData != "") {
				transformData(inputData);
			}
		}
	}

	private void transformData(String inputData) {
		String[] data = inputData.split(" ");
		String transformedData = "";
		for(int i = 0; i < data.length; i++){
			char[] word = inputData.toCharArray();
			stemmerAlgorithm.add(word, word.length);
			stemmerAlgorithm.stem();
		    transformedData += stemmerAlgorithm.toString() + " ";
		}
		this.outputPipe.getInput(transformedData);
	}



}
