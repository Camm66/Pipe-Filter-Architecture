package CameronMorales.PipeFilter;

import java.util.LinkedList;
import java.util.Queue;

public class Pipe {

	Queue<String> inputQueue = new LinkedList<String>();

	public void getInput(String inputData){
		inputQueue.offer(inputData);
	}

	public String sendOutput() {
		return inputQueue.poll();
	}
}
