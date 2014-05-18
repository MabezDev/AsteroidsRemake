package com.mabez.game.managers;

/**
 * Created by user on 18/05/2014.
 */
/**
 * Created by user on 03/05/2014.
 */
public class MyKeys {

    protected static int NUM_KEYS = 7;
    public static int W = 0;
    public static int A  =1;
    public static int S  = 2;
    public static int D = 3;
    public static int SPACE = 4;
    public static int SHIFT = 5;
    public static int ESCAPE = 6;
    private static boolean[] keys = new boolean[NUM_KEYS];;
    private static boolean[] pkeys = new boolean[NUM_KEYS];;




    public static void update(){
        for(int i =0;i<NUM_KEYS;i++){
            pkeys[i] = keys[i];
        }
    }

    public static void setKeyState(int k,boolean b){
        keys[k]= b;
    }




    public static boolean isDown(int k){

        return keys[k];

    }
    public static boolean isPressed(int k){
        return !pkeys[k] && keys[k];
    }



}
