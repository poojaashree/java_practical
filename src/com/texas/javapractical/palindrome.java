package com.texas.javapractical;

import java.util.Scanner;

public class palindrome {
    static boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String word = sc.nextLine();

        if (isPalindrome(word))
            System.out.println(word + " is a Palindrome.");
        else
            System.out.println(word + " is NOT a Palindrome.");
    }
}
