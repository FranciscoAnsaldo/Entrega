package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RegExGenerator {

    private int maxLength;

    public RegExGenerator(int maxLength) {
        this.maxLength = maxLength;
    }

    public List<String> generate(String regEx, int numberOfResults) {
        ArrayList arr = new ArrayList<String>();
        RegExParser parser = new RegExParser();
        ArrayList tokens = parser.parse(regEx);
        for (int i = 0; i < numberOfResults; i++) {
            arr.add(getString(tokens));
        }
        return arr;
    }

    private String getString(ArrayList tokens){
        String str  = new String();
        for (int i = 0; i < tokens.size(); i++){
            str = str + getPartialString((String)tokens.get(i));;
        }
        return str;
    }

    private String getPartialString(String s) {
        String str;
        switch (s.charAt(0)) {
            case '.':
                str = processPoint(s);
                break;
            case '[':
                str = processSet(s);
                break;
            case '\\':
                str = processBar(s);
                break;
            default:
                str = processLiterals(s);
                break;
        }
        return str;
    }

    private String processPoint(String s){
        String str = new String();
        Random rand = new Random();
        if (s.length() == 1){
            str = String.valueOf((char)rand.nextInt(256));
        }
        else {
            int len = getStrLen(s.charAt(1));
            for (int i = 0; i < len; i++){
                str = str + String.valueOf((char)rand.nextInt(256));
            }
        }
        return str;
    }

    private String processSet(String s){
        String str = new String();
        Random rand = new Random();
        if (s.charAt(s.length() - 1) == ']'){
            str = String.valueOf(s.charAt((rand.nextInt((s.length() - 2)) + 1)));
        }
        else {
            int len = getStrLen(s.charAt(s.length() - 1));
            for (int i = 0; i < len; i++){
                str = str + String.valueOf(s.charAt((rand.nextInt((s.length() - 3)) + 1)));
            }
        }
        return str;
    }

    private String processLiterals(String s){
        String str = new String();
        if (s.length() == 1){
            str = s;
        }
        else {
            int len = getStrLen(s.charAt(1));
            for (int i = 0; i < len; i++){
                str = str + String.valueOf(s.charAt(0));
            }
        }
        return str;
    }

    private String processBar(String s){
        if(s.length() > 1){
            return String.valueOf(s.charAt(1));
        }
        return s;
    }

    private int getStrLen(char c){
        int len = 0;
        Random rand = new Random();
        switch (c){
            case '?':
                len = rand.nextInt(2);
                break;
            case '*':
                len = rand.nextInt(maxLength + 1);
                break;
            case '+':
                len = (rand.nextInt(maxLength)) + 1;
                break;
        }
        return len;
    }

}