package CameronMorales.PipeFilter;

public class FilterRemoveNonAlpha extends Filter {

	FilterRemoveNonAlpha(Pipe _inputPipe, Pipe _outputPipe) {
		super(_inputPipe, _outputPipe);
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
				transformedData = data[i].replaceAll("[^a-zA-Z]", "") + " ";
			}
		this.outputPipe.getInput(transformedData);
	}
}
