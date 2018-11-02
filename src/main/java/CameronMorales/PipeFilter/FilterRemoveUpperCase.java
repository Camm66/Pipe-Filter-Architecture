package CameronMorales.PipeFilter;

public class FilterRemoveUpperCase extends Filter {

	FilterRemoveUpperCase(Pipe _inputPipe, Pipe _outputPipe) {
		super(_inputPipe, _outputPipe);
	}

	public void run(){
		System.out.println("Filter-RemoveUpperCase Begins: " + System.currentTimeMillis());
		while(true){
			String inputData = this.inputPipe.sendOutput();
			if(this.poisonPill.equals(inputData))
				break;
			else if(inputData != "" && inputData != null){
					transformData(inputData);
			}
			else
				continue;
		}
		this.outputPipe.getInput(this.poisonPill);
		System.out.println("Filter-RemoveUpperCase Ends: " + System.currentTimeMillis());
	}

	private void transformData(String inputData) {
		String outputData = inputData.toLowerCase();
		this.outputPipe.getInput(outputData);
	}
}
