/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
    public static void main(String a[]){
        CountThread first = new CountThread(0,99);
        CountThread second = new CountThread(99,199);
        CountThread third = new CountThread(200,299);
        first.run();
        second.run();
        third.run();
    }
    
}
