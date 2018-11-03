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
		while(this.inputQueue.isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Pipe blocking failure!");
			}
		}
		return inputQueue.poll();
	}
}
