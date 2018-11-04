package CameronMorales.PipeFilter;

public class FilterRemoveUpperCase extends Filter {

	FilterRemoveUpperCase(Pipe _inputPipe, Pipe _outputPipe) {
		super(_inputPipe, _outputPipe);
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
		String outputData = inputData.toLowerCase();
		this.outputPipe.getInput(outputData);
	}
}
