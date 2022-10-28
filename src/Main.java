import java.io.IOException;
import java.util.Enumeration;

import static java.lang.System.currentTimeMillis;

public class Main {
    public static void main(String[] args) throws IOException {
        String grammar = "Files/grammar1.txt";
        Parser parser = new Parser(grammar);
        String currentInputString;

        Enumeration<String> inputString1 = new Enumeration<>() {
            private String current = "";
            @Override
            public boolean hasMoreElements() {
                return true;
            }
            @Override
            public String nextElement() {
                current = "((((((((((((((((((((" + current + "))))))))))))))))))))";
                return current;
            }
        };

        Enumeration<String> inputString2 = new Enumeration<>() {
            private String current = "";
            @Override
            public boolean hasMoreElements() {
                return true;
            }
            @Override
            public String nextElement() {
                current = "()()()()()()()()()()" + current + "()()()()()()()()()()";
                return current;
            }
        };

        currentInputString="";
        while (currentInputString.length() < 2500) {
            currentInputString = inputString1.nextElement();
            System.out.println("Input length : "+currentInputString.length());
            System.gc();
            System.out.println("Bottom-up :");
            long startTime1 = currentTimeMillis();
            System.out.println(parser.parseBU(currentInputString));
            long runningTime1 = currentTimeMillis() - startTime1;
            System.out.println("Running time : "+runningTime1);
            System.out.println("Counter : "+parser.getCounter());
            System.gc();
            System.out.println("Top-down :");
            long startTime2 = currentTimeMillis();
            System.out.println(parser.parseTD(currentInputString));
            long runningTime2 = currentTimeMillis() - startTime2;
            System.out.println("Running time : "+runningTime2);
            System.out.println("Counter : "+parser.getCounter());
            System.out.println("------------------");
        }
    }
}
