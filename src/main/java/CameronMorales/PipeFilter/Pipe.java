package CameronMorales.PipeFilter;

import java.util.LinkedList;
import java.util.Queue;

public class Pipe {

	Queue<String> inputQueue = new LinkedList<String>();

	public synchronized void getInput(String inputData){
		inputQueue.offer(inputData);
		notify();
	}

	public synchronized String sendOutput() {
		while(inputQueue.isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("PIPE FAILURE!");
			}
		}
		return inputQueue.poll();

	}
}
