package com.company;

public class Operations {

    public int factorial(int number){
        if(number<0) {
            return -1;
        }
        else if(number==0 || number==1) {
            return 1;
        }
        else {
            return number*factorial(number-1);
        }
    }

    public int sumOdds(int number) {
        int sum=0;
        for(int i=1;i<=number;i+=2) {
            sum+=i;
        }
        return sum;
    }

    public String concateText(String a, String b) {
        a = a.concat(b);
        String c = "Testingisgood";
        a = a.concat(c);
        return a;
    }
}
