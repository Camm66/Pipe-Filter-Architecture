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
		System.out.println("Filter-RootForms Begins: " + System.currentTimeMillis());
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
		System.out.println("Filter-RootForms Ends: " + System.currentTimeMillis());
	}

	private String transformData(String inputData) {
		char[] word = inputData.toCharArray();
		stemmerAlgorithm.add(word, word.length);
		stemmerAlgorithm.stem();
		String outputData = stemmerAlgorithm.toString();
		return outputData;
	}



}
