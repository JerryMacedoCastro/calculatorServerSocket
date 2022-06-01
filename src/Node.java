package src;

public class Node {
    public char value;
    public Node leftSon;
    public Node rightSon;

    public Node(char x) {
        value = x;
    }

    public char getValue() {
        return value;
    }

    public void show() {
        System.out.print(value);
    }
}