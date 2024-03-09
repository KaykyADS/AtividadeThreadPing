package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadPing extends Thread{
	private StringBuffer bufferPing = new StringBuffer();
	private String process;
	private int i;
	
	public ThreadPing(String process, int i) {
		this.process = "ping -4 -c 10 " + process;
		this.i = i;
	}

	@Override
	public void run() {
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine().concat(process);
			linha = buffer.readLine();
			linha = buffer.readLine();
			
			while (linha != null) {
				/*bufferPing.append(linha);
				bufferPing.append(" ");
				bufferPing.append("\n");*/
				if (linha.contains("time=")) {
					String[] split = linha.split("time=");
					switch (i) {
						case 1:
							System.out.println("UOL tempo: " + split[1]);
							break;
						case 2:
							System.out.println("Terra tempo: " + split[1]);
							break;
						case 3:
							System.out.println("Google tempo: " + split[1]);
							break;
					}
				} synchronized (ThreadPing.class) {
					if (linha.contains("rtt")) {
						String split[] = linha.split("/");
						switch (i) {
						case 1:
							System.out.println("UOL média --> " + split[4]);
							break;
						case 2:
							System.out.println("Terra média --> " + split[4]);
							break;
						case 3:
							System.out.println("Google média --> " + split[4]);
							break;
						}
					}
				}
				linha = buffer.readLine();
			}
			//System.out.println(bufferPing);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
