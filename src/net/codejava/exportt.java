package net.codejava;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;


public class exportt (String []args){

       Scanner sc=new Scanner(System.in);

       System.out.println("Enter How Data need to be exported \n1.Yearwise\n2.Coursewise");

       int a=sc.nextInt();

       if(a>2){
    	   System.out.println("Enter valid Number ! ");
    	   break;
       }

       System.out.println("Enter name to create your file : ");
       String filename=sc.next();
       File file = new File(filename.txt);

       if(!file.exists()){
           file.createNewFile();
       }

       PrintWriter pw=new PrintWriter(file);
   
}