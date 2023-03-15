package com.adhiraj.dsaalgo.leetcode.week5;

public class Lc67 {
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1;
        StringBuilder res = new StringBuilder();
        char carry = '0';

        while(i >= 0 && j >= 0) {
            char x = a.charAt(i), y = b.charAt(j);
            String calc = add(x, y, carry);
            res.append(calc.charAt(1));
            carry = calc.charAt(0);
            i--;
            j--;
        }

        while (i >= 0) {
            char x = a.charAt(i);
            String calc = add(x, '0', carry);
            res.append(calc.charAt(1));
            carry = calc.charAt(0);
            i--;
        }

        while (j >= 0) {
            char y = b.charAt(j);
            String calc = add('0', y, carry);
            res.append(calc.charAt(1));
            carry = calc.charAt(0);
            j--;
        }

        if (carry == '1') res.append(carry);

        return res.reverse().toString();

    }

    private String add(char a, char b, char carry) {
        if (carry == '0') {
            if (a == '0' && b == '0')
                return "00";
            if (a == '0' || b == '0')
                return "01";
            return "10";
        } else {
            if (a == '0' && b == '0')
                return "01";
            if (a == '0' || b == '0')
                return "10";
            return "11";
        }
    }
}