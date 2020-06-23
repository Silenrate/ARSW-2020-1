/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

import java.util.ArrayList;

/**
 *
 * @author hcadavid
 */
public class CountThread extends Thread{
    int limInf;
    int limSup;

    public CountThread(int limInf, int limSup){
        this.limInf = limInf;
        this.limSup = limSup;
    }
    public void run(){
        for (int i=limInf+1;i<limSup;i++){
            System.out.println(i);
        }
    }
}
