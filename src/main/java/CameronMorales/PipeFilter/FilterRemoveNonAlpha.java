package CameronMorales.PipeFilter;

import java.util.regex.Pattern;

public class FilterRemoveNonAlpha extends Filter {

	FilterRemoveNonAlpha(Pipe _inputPipe, Pipe _outputPipe) {
		super(_inputPipe, _outputPipe);
	}

	public void run(){
		System.out.println("Filter-RemoveNonAlpha Begins: " + System.currentTimeMillis());
		while(true){
			String inputData = this.inputPipe.sendOutput();
			if(inputData == null || inputData == "")
				continue;
			else if(inputData.equals(this.poisonPill)){
				System.out.println(inputData);
				break;}
			else{
					transformData(inputData);
			}
		}
		this.outputPipe.getInput(this.poisonPill);
		System.out.println("Filter-RemoveNonAlpha Ends: " + System.currentTimeMillis());
	}

	private void transformData(String inputData) {
		Pattern p = Pattern.compile("[^a-zA-Z]");
		String[] data = inputData.split(" ");

		for(int i = 0; i < data.length; i++){
			if(!p.matcher(data[i]).find()){
				boolean added = this.outputPipe.getInput(data[i]);
				if(!added)
					System.out.println("we have a queue problem");
			}
		}
	}
}
