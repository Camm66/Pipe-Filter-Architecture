package CameronMorales.PipeFilter;

import java.util.LinkedList;
import java.util.Queue;

public class Pipe {

	Queue<String> inputQueue = new LinkedList<String>();

	public boolean getInput(String inputData){
		return inputQueue.offer(inputData);
	}

	public String sendOutput() {
		return inputQueue.poll();
	}
}
