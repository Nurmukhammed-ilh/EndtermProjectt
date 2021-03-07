package com.company;

import com.company.Application;

import java.util.Scanner;

public class Main { //creation class
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Application app = new Application();
        int d=0;
        System.out.println("Welcome to the FBI database! ");
        System.out.println("Please enter the password to gain access: ");
        while (d<=3) { //conditions when entering a password
            if(d==3){
                System.out.println("Not Allowed!");
                System.exit(0);
            }
            int count=3-d;
            System.out.println("You have "+count+" attempts left then access will be blocked");
            int password=scanner.nextInt();
            if(password==123) {
                app.start();
                break;
            }
            else{
                System.out.println("Wrong password!");
                d++;
            }
        }
    }
}
