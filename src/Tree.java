package src;
import java.util.Scanner;
import java.util.Stack;

public class Tree {
    private Node root;
    private static String postFix;

    public Tree() {
        root = null;
    }

    public void insert(String s) {
    	int i = 0;
        Conversion c = new Conversion(s);
        NodeStack stk = new NodeStack(s.length());
        char symbol = s.charAt(i);
        Node newNode;
        
        s = c.toPost();
        s += "#";
      
        while (symbol != '#') {
            if (symbol >= '0' && symbol <= '9' || symbol >= 'A' && symbol <= 'Z' || symbol >= 'a' && symbol <= 'z') {
                newNode = new Node(symbol);
                stk.push(newNode);
            } else if (symbol == '+' || symbol == '-' || symbol == '/' || symbol == '*') {
                Node ptr1 = stk.pop();
                Node ptr2 = stk.pop();
                newNode = new Node(symbol);
                newNode.leftSon = ptr2;
                newNode.rightSon = ptr1;
                stk.push(newNode);
            }
            symbol = s.charAt(++i);
        }
        root = stk.pop();
    }

   
    public void show() {
    	//System.out.print("POSFIXA: ");
    	postOrder(root);
    	System.out.print("RESULTADO: " + calc());        
    }

    public String getPostFix() {
        return postFix;
    }

    private void postOrder(Node root) {
        if (root != null) {
            postOrder(root.leftSon);
            postOrder(root.rightSon);
            //root.show();

            if(postFix != null)
                postFix = postFix + " " + Character.toString(root.getValue());
            else
                postFix = Character.toString(root.getValue());
        }
    }
    
    public static int calc() {
     	Stack<Integer> stack = new Stack<Integer> ();
    	try (Scanner expression = new Scanner(postFix)) {
			while (expression.hasNext()) {
				if (expression.hasNextInt()) {
				    stack.push(expression.nextInt());
				} else {
					int num2 = stack.pop();
					int num1 = stack.pop();
					
					String op = expression.next();
					
					if (op.equals("+")) {
						stack.push(num1 + num2);
					} else if (op.equals("-")) {
						stack.push(num1 - num2);
					} else if (op.equals("*")) {
						stack.push(num1 * num2);
					} else {
						stack.push(num1 / num2);
					}
			    }
			}
		}
    	
    	return stack.pop();
    }
}