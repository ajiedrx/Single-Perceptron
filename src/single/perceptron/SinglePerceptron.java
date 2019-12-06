package single.perceptron;

import java.io.IOException;
import java.util.Scanner;

/*
 * @author ajied
 */
public class SinglePerceptron {
    public static void main(String[] args) throws IOException {
        double sum;
        int datapick = 0;
        String pil = " ";
        Data data = new Data();
        Scanner input = new Scanner(System.in);
        System.out.println("Pilih data : ");
        System.out.println("1. Ruspini  : ");
        System.out.println("2. AND : ");
        System.out.println("3. OR : ");
        datapick = input.nextInt();
        data.setSWITCH_DATA(datapick);
        System.out.println("EPOCH : ");
        data.setEPOCH(input.nextInt());
                
        if(datapick == 1){
            int x,y;
            data.readFile("C:/ruspini.csv");
            data.assignTarget();
            System.out.println("First weights : ");
            data.generateWeights();
            for(int i = 0; i < data.weights.length; i++){
                System.out.println(data.weights[i]+",");
            }
            data.learning();
            System.out.println("Last weight : ");
            for(int i = 0; i < data.weights.length; i++){
                System.out.println(data.weights[i]+",");
            }
            if(data.getAT() != 0){
                System.out.println("Optimal pada epoch ke : "+data.getAT());
            }
            else
                System.out.println("Belum optimal");
            do{
                System.out.println("\n\nTesting Data : ");
                System.out.println("Masukkan input 1 : ");
                x = input.nextInt();
                System.out.println("Masukkan input 2 : ");
                y = input.nextInt();
                sum = (1*data.weights[0])+(x*data.weights[1])+(y*data.weights[2]);
                if(sum < 0){
                    System.out.println("Output 0");
                }
                else
                    System.out.println("Output 1");
                System.out.print("Input lagi ? (Y/N) : ");
                pil = input.next();
            }while(!pil.equalsIgnoreCase("N"));
        if(datapick == 2){
            data.readFile("C:/ruspini.csv");
            data.assignTarget();
            System.out.println("First weights : ");
            data.generateWeights();
            for(int i = 0; i < data.weights.length; i++){
                System.out.println(data.weights[i]+",");
            }
            data.learning();
            System.out.println("Last weight : ");
            for(int i = 0; i < data.weights.length; i++){
                System.out.println(data.weights[i]+",");
            }
            
            if(data.getAT() != 0){
                System.out.println("Optimal pada epoch ke : "+data.getAT());
            }
            else
                System.out.println("Belum optimal");
        }
        
        if(datapick == 3){
            data.readFile("C:/ruspini.csv");
            data.assignTarget();
            System.out.println("First weights : ");
            data.generateWeights();
            for(int i = 0; i < data.weights.length; i++){
                System.out.println(data.weights[i]+",");
            }
            data.learning();
            System.out.println("Last weight : ");
            for(int i = 0; i < data.weights.length; i++){
                System.out.println(data.weights[i]+",");
            }
            
            if(data.getAT() != 0){
                System.out.println("Optimal pada epoch ke : "+data.getAT());
            }
            else
                System.out.println("Belum optimal");
        } 
    }
    }
}
