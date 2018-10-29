package CameronMorales.PipeFilter;

import Algorithms.PorterStemmer;

public class FilterRootForms extends Filter {

	PorterStemmer stemmerAlgorithm = new PorterStemmer();

	FilterRootForms(Pipe _inputPipe, Pipe _outputPipe) {
		super(_inputPipe, _outputPipe);
	}

	public void run(){
		while(true){
			String inputData = this.inputPipe.sendOutput();
			if(inputData != this.poisonPill){
				if(inputData != null){
					String outputData = transformData(inputData);
					this.outputPipe.getInput(outputData);
				}
			} else {
				this.outputPipe.getInput(this.poisonPill);
				System.out.println("Closing root forms");
				break;
			}
		}
	}

	private String transformData(String inputData) {
		char[] word = inputData.toCharArray();
		stemmerAlgorithm.add(word, word.length);
		stemmerAlgorithm.stem();
		String outputData = stemmerAlgorithm.toString();
		return outputData;
	}



}
