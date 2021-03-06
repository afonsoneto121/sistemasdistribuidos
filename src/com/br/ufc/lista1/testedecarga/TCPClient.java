package com.br.ufc.lista1.testedecarga;
import java.net.*;
import java.io.*;
public class TCPClient {
	
	public void execultar (String args[],int serverPort) {
	// arguments supply message and hostname of destination
	Socket s = null;
	    try{
		   	s = new Socket(args[0], serverPort);
		   	DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =
				new DataOutputStream( s.getOutputStream());
			out.writeUTF(args[1]);        	// UTF is a string encoding see Sn 4.3
			String data = in.readUTF();	      			
			//System.out.println("Received: "+ data);      
	    } catch (UnknownHostException e){System.out.println("Sock:"+e.getMessage()); 
	    } catch (EOFException e){ System.out.println("EOF:"+e.getMessage());
	    } catch (IOException e){ System.out.println("IO:"+e.getMessage());
		} finally { if(s!=null) { try { s.close(); } catch (IOException e){System.out.println("close:"+e.getMessage()); } }
		}
  	}
}
