/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package single.perceptron;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author ajied
 */
public class Data {
    ArrayList<DataSequence> sequences = new ArrayList<>();
    ArrayList<Integer> targets =  new ArrayList<>();
    double[] weights = new double[3];
    int EPOCH = 100000;
    double MIU = 0.1;
    int BIAS = 1;
    int TRESHOLD = 0;
    int SWITCH_DATA = 0;
    int eError = 0;
    int AT = 0;
    boolean optimal;
        
    public void readFile(String fileName) throws IOException {
        int[] dataTemp = new int[225];
        int[][] dataSet = new int[75][3];
        int[][] and = {{0,0,0},
                       {0,1,0},
                       {1,0,0},
                       {1,1,1}};
        int[][] or = {{0,0,0},
                      {0,1,0},
                      {1,0,0},
                      {1,1,1}};
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        try {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(",");
                line = br.readLine();
            }
            StringTokenizer st = new StringTokenizer(sb.toString(),",");
            for(int i=0;i<dataTemp.length;i++){
                dataTemp[i] = Integer.parseInt(st.nextToken().replaceAll("\n",""));
            }
            for (int i = 0, k=0; i < 75; i++)
                for (int jl = 0; jl < 3; jl++)
                    dataSet[i][jl] = dataTemp[k++];
         } finally {
            br.close();
        }
        init(dataSet, and, or);
    }

    public void init(int[][] dataSet, int[][] and, int[][] or) {
        if(SWITCH_DATA == 1){
            for(int i = 0; i < dataSet.length;i++){
                if(dataSet[i][2] == 1 || dataSet[i][2] == 3 ){
                    dataSet[i][2] = 0;
                }
            }

            for(int i = 0; i < dataSet.length;i++){
                if(dataSet[i][2] == 2 || dataSet[i][2] == 4){
                    dataSet[i][2] = 1;
                }
            }
            for(int i = 0; i < dataSet.length; i++){
                sequences.add(new DataSequence(dataSet[i][0],dataSet[i][1],dataSet[i][2]));        
            }
        }
        if(SWITCH_DATA == 2){
            for(int i = 0; i < and.length; i++){
                sequences.add(new DataSequence(and[i][0],and[i][1],and[i][2]));        
            }
        }
        
        if(SWITCH_DATA == 3){
            for(int i = 0; i < or.length; i++){
                sequences.add(new DataSequence(or[i][0],or[i][1],or[i][2]));        
            }
        }
    }

    public double[] generateWeights(){
        Random random = new Random();
        for(int i = 0; i < weights.length; i++){
            weights[i] = Math.random() * (1 - (-1)) + (-1);
        }
        return weights;
    }
    
    public void assignTarget(){
        for(int i = 0; i < sequences.size(); i++){
            targets.add(sequences.get(i).getZ());
       }
    }
    
    public void updateWeights(int errorIndex, int err){
        weights[0] = weights[0] + (MIU*1*err);
        weights[1] = weights[1] + (MIU*sequences.get(errorIndex).getX()*err); 
        weights[2] = weights[2] + (MIU*sequences.get(errorIndex).getY()*err); 
    }
    
    public void learning(){
        int output = 0;
        double sum = 0;
        int err = 0;
        for(int i = 0; i < EPOCH; i++){
            eError = 0;
            for(int j = 0; j < sequences.size(); j++){
                sum = (BIAS*weights[0])+(sequences.get(j).getX()*weights[1])+
                        (sequences.get(j).getY()*weights[2]);
                if(sum < 0){
                    output = 0;
                }
                else
                    output = 1;
                err = targets.get(j)-output;
                if(err != 0){
                    updateWeights(j, err);
                    eError++;
                }
            }
            if(eError == 0){
                if(!optimal){
                    AT = i;
                    optimal = true; 
                }
            }
        }
    }

    public int getSWITCH_DATA() {
        return SWITCH_DATA;
    }

    public void setSWITCH_DATA(int SWITCH_DATA) {
        this.SWITCH_DATA = SWITCH_DATA;
    }

    public int getEPOCH() {
        return EPOCH;
    }

    public void setEPOCH(int EPOCH) {
        this.EPOCH = EPOCH;
    }

    public int geteError() {
        return eError;
    }

    public int getAT() {
        return AT;
    }

    public boolean isOptimal() {
        return optimal;
    }
    
    
    
}   

