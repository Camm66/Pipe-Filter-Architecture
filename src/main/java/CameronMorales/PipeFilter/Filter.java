package CameronMorales.PipeFilter;

public class Filter implements Runnable {
	Pipe inputPipe;
	Pipe outputPipe;
	String poisonPill;

	Filter(Pipe _inputPipe, Pipe _outputPipe){
		this.inputPipe = _inputPipe;
		this.outputPipe = _outputPipe;
	}

	public String getInput(){
		String inputData = inputPipe.sendOutput();
		return inputData;
	}

	public void sendOutput(String transformedData){
		outputPipe.getInput(transformedData);
	}

	public void setPoisonPill(String _poisonPill){
		this.poisonPill = _poisonPill;
	}

	public void run() {}
}
