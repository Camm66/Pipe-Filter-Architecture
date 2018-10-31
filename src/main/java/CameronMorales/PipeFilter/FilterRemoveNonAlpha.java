package CameronMorales.PipeFilter;

import java.util.regex.Pattern;

public class FilterRemoveNonAlpha extends Filter {

	FilterRemoveNonAlpha(Pipe _inputPipe, Pipe _outputPipe) {
		super(_inputPipe, _outputPipe);
	}

	public void run(){
		while(true){
			String inputData = this.inputPipe.sendOutput();
			if(inputData != this.poisonPill){
				if(inputData != null){
					transformData(inputData);
				}
			} else if(inputData == this.poisonPill) {
				this.outputPipe.getInput(this.poisonPill);
				break;
			}
		}
	}

	private void transformData(String inputData) {
		Pattern p = Pattern.compile("[^a-zA-Z]");
		String[] data = inputData.split(" ");

		for(int i = 0; i < data.length; i++){
			if(!p.matcher(data[i]).find()){
				this.outputPipe.getInput(data[i]);
			}
		}
	}
}
