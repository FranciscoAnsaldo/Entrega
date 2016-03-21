package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;

public class RegExParser {

    public ArrayList parse (String regEx){
        ArrayList tokens = new ArrayList<String>();
        String token;
        for (int i = 0;i < regEx.length();) {
            token = getToken(regEx, i);
            tokens.add(token);
            i = i + token.length();
        }
        return tokens;
    }

    private String getToken(String regEx, int pos){
        String str;
        str = String.valueOf(regEx.charAt(pos));
        if(pos + 1 < regEx.length()) {
            switch (regEx.charAt(pos)) {
                case '[':
                    int i;
                    for (i = pos + 1; regEx.charAt(i) != ']'; i++) {
                        str = str + String.valueOf(regEx.charAt(i));
                    }
                    str = str + String.valueOf(regEx.charAt(i));
                    if (i + 1 < regEx.length() && isModifier(regEx.charAt(i + 1))) {
                        str = str + String.valueOf(regEx.charAt(i + 1));
                    }
                    break;
                case '\\':
                    str = str + String.valueOf(regEx.charAt(pos + 1));
                    break;
                default:
                    if (isModifier(regEx.charAt(pos + 1))) {
                        str = str + String.valueOf(regEx.charAt(pos + 1));
                    }
                    break;
            }
        }
        return str;
    }

    private boolean isModifier(char c){
        return c == '?' || c == '+' || c == '*';
    }

}
