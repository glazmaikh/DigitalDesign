package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Unpacking {

    private static class Element {
        public final int factor;
        public final StringBuilder sb;

        public Element(int factor) {
            this.factor = factor;
            sb = new StringBuilder();
        }
    }
    private static Stack<Element> stack = new Stack<>();

    public static void main(String... args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        unpackingString(str);
    }

    public static void unpackingString(String str){
        String str1 = str.replaceAll("[^a-zA-Z\\d\\[\\]]","");
        char[]chars = str1.toCharArray();

        int factor = 0;
        for (char ch : chars) {
            if (Character.isDigit(ch)) {
                factor = Character.getNumericValue(ch);
            } else if (ch == '[') {
                stack.push(new Element(factor));
                factor = 0;
            } else if (ch == ']') {
                Element elem = stack.pop();
                String s = elem.sb.toString();
                append(elem.factor, s);
                factor = 0;
            } else {
                append(factor, Character.toString(ch));
                factor = 0;
            }
        }
    }
    public static void append(int factor, String s) {
        int n = (factor == 0) ? 1 : factor;
        for (int i = 0; i < n; ++i) {
            if (stack.empty()) {
                System.out.print(s);
            } else {
                stack.peek().sb.append(s);
            }
        }
    }
}

