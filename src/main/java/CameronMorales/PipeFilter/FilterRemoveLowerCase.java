package CameronMorales.PipeFilter;

public class FilterRemoveLowerCase extends Filter {

	FilterRemoveLowerCase(Pipe _inputPipe, Pipe _outputPipe) {
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
				System.out.println("Closing non lower");
				break;
			}
		}
	}

	private void transformData(String inputData) {
		String outputData = inputData.toLowerCase();
		this.outputPipe.getInput(outputData);
	}
}
