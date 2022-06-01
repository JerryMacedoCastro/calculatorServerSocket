import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CalculadoraServerSocket {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServerSocket welcomeSocket;
		DataOutputStream socketOutput;     	
	    DataInputStream socketInput;
	    BufferedReader socketEntrada;
	    Calculadora calc = new Calculadora();
		try {
			/*
			
			welcomeSocket = new ServerSocket(9090);
			int i=0; //número de clientes
			  
		      System.out.println ("Servidor no ar");
		      while(true) { 
		  
		           Socket connectionSocket = welcomeSocket.accept(); 
		           i++;
		           System.out.println ("Nova conexão");
		           
		           //Interpretando dados do servidor
		           socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	               String operacao= socketEntrada.readLine();
	               String oper1=socketEntrada.readLine();
	               String oper2=socketEntrada.readLine();
	               
	               String result = "null";
	               switch(operacao) {
	               	case "1":
	               		result= ""+calc.soma(Double.parseDouble(oper1),Double.parseDouble(oper2));
	               		break;
	               	case "2":
	               		result= ""+calc.subtracao(Double.parseDouble(oper1),Double.parseDouble(oper2));
	               		break;
	               	case "3":
	               		result= ""+calc.divisao(Double.parseDouble(oper1),Double.parseDouble(oper2));
	               		break;
	               	case "4":
	               		result= ""+calc.multiplicacao(Double.parseDouble(oper1),Double.parseDouble(oper2));
	               		break;
	               }
	               
	               //Enviando dados para o servidor
	               socketOutput= new DataOutputStream(connectionSocket.getOutputStream());     	
		           socketOutput.writeBytes(result+ '\n');
		           System.out.println (result);	           
		           socketOutput.flush();
		           socketOutput.close();            
	      }
	      */
			String a = "";
			Scanner sc = new Scanner(System.in);
	        while (!a.equals("q"))
	        {
	            Tree t1 = new Tree();
	            System.out.println("Digite Q para sair ou uma express�o no formato. Ex. ((8+5)*(7-1)+(5*5))");
	            a = sc.nextLine();
	            t1.insert(a);

	            if(!a.equals("q"))
	            {
	            t1.show();
	                System.out.println("\n");
	            }else
	            {
	                System.out.println("Obrigado!");
	            }
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		 
	    
	}

}
