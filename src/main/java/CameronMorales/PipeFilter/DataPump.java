package CameronMorales.PipeFilter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataPump implements Runnable {
	private Pipe outputPipe;
	private String poisonPill;
	private String filename;

	DataPump(Pipe _outputPipe){
		this.outputPipe = _outputPipe;
	}

	public void setPoisonPill(String _poisonPill) {
		this.poisonPill = _poisonPill;
	}

	public void setFilename(String _filename){
		this.filename = _filename;
	}

	public void run(){
		System.out.println("DataPump Begins: " + System.currentTimeMillis());
		while(true){
			try {
				Thread.sleep(10);
			} catch (InterruptedException ignore) {
			}
			try {
				String line;
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.filename)));
				while ((line = br.readLine()) != null){
					this.outputPipe.getInput(line);
				}
				br.close();
				break;
			}catch (IOException e){
				System.out.println("Input file not found!");
			}
		}
		this.outputPipe.getInput(this.poisonPill);
		System.out.println("DataPump Ends: " + System.currentTimeMillis());
	}
}
