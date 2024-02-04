package de.edward;
import java.lang.Math;

public class Main {
    Main (){
        System.out.println("HOI!\n");
        /*
        // poof of concept
        System.out.println(Math.pow(5,2) + Math.pow(3,2)); // dist
        System.out.println((Math.atan2(5,3)) * (180/Math.PI)); // deg
        System.out.println(180 - (Math.atan2(5,3)) * (180/Math.PI) - 45); // rev deg
        //System.out.println(Math.sin(Math.toRadians(75.96)));
        System.out.println(Math.sqrt(Math.pow(5,2) + Math.pow(3,2)) * Math.sin(Math.toRadians(180 - (Math.atan2(5,3)) * (180/Math.PI) - 45))); // x pos
        System.out.println(Math.sqrt((Math.pow(5,2) + Math.pow(3,2)) - Math.pow(Math.sqrt(Math.pow(5,2) + Math.pow(3,2)) * Math.sin(Math.toRadians(180 - (Math.atan2(5,3)) * (180/Math.PI) - 45)) , 2)) * -1); // y pos
        System.out.println("");
         */

        Chair c = new Chair(-8,0,-4, 2, 16, 16, 45);

        for(int i = 0 ; i <= 13 ; i++ ){
            System.out.println(i + 1 + "."); //prints no. of point
            for(int j = 0 ; j <= 2 ; j++){
                System.out.println(c.getP(i,j)); // 13 , 2
            }
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        Main m = new Main();
        System.out.println("\n\n ======= This code has been provided by TheBoringEdward =======\n");
    }
}
