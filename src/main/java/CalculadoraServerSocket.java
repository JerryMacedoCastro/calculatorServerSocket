import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.yaml.snakeyaml.Yaml;

import javax.xml.parsers.*;
import java.io.*;

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
			int i=0; //nÃºmero de clientes
			  
		      System.out.println ("Servidor no ar");
		      while(true) { 
		  
		           Socket connectionSocket = welcomeSocket.accept(); 
		           i++;
		           System.out.println ("Nova conexÃ£o");
		           
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
			boolean endLoop = false;
	        while (!endLoop)
	        {
	            Tree t1 = new Tree();
	            System.out.println("Comandos: ");
	            System.out.println("Expressão no formato Ex. ((8+5)*(7-1)+(5*5)) -> Calcula o resultado");
	            System.out.println("x = Processa fórmula descrita em XML");
	            System.out.println("y = Processa fórmula descrita em YAML");
	            System.out.println("p = Processa fórmula descrita em Protocol Buffer");
	            System.out.println("s = Sair");
	            a = sc.nextLine();
	            
	            switch(a) {
	            	case "s":
	            		System.out.println("Fim!");
	            		return;
	            	case "y":
	            		a = ParseYAML("formula.yaml");
	            		break;
	            	case "x":
	            		a = ParseXML("formula.xml");
	            		break;
	            	case "p":
	            		break;
	            	default:
		                break;
	            }
	            t1.insert(a);
        		t1.show();
        		System.out.println("\n");
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public static String ParseXML(String fileName) {
		File xmlFile = new File(fileName);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			NodeList operandList = ((Element) doc.getElementsByTagName("operands").item(0)).getElementsByTagName("operand");
			NodeList operationList = ((Element) doc.getElementsByTagName("operations").item(0)).getElementsByTagName("operation");
			StringBuilder operation = new StringBuilder();
			for(int i = 0; i < operandList.getLength(); i++) {
				operation.append(operandList.item(i).getTextContent());
				if (operationList.getLength() > i) {
					operation.append(operationList.item(i).getTextContent());
				}
			}
			return operation.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static String ParseYAML(String fileName) {
		Yaml yaml = new Yaml();
		try {
			InputStream stream = new FileInputStream(fileName);
			Map map = (Map) yaml.load(stream);
			if (map == null || map.isEmpty() == true) {
	            throw new RuntimeException("Leitura do arquivo falhou.");
	        }
			ArrayList operandList = (ArrayList) map.get("operands");
			ArrayList operationList = (ArrayList) map.get("operations");
			StringBuilder operation = new StringBuilder();
			for(int i = 0; i < operandList.size(); i++) {
				operation.append(operandList.get(i));
				if (operationList.size() > i) {
					operation.append(operationList.get(i));
				}
			}
			return operation.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public static String ParseProtocolBuffer(String fileName) {
		return "";
	}
}
