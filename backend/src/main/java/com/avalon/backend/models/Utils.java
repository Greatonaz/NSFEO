package com.avalon.backend.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Tonaz on 4/11/2015.
 */
public class Utils {

    public static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    private static int showRandomInteger(int aStart, int aEnd, double randomDouble){

        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long)aStart + 1;

        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * randomDouble);
        int randomNumber =  (int)(fraction + aStart);

        return randomNumber;
    }

    public static List<Integer> randomIntegerList(int min, int max, int listSizeRequired){

        Random randomGenerator = new Random();

        // We get a set to keep track of the unique integers generated
        HashSet<Integer> randomNumberSet = new HashSet<Integer>();

        // To keep track of the list size required
        int counter = 0;
        int newRandomNumber;

        while(counter < listSizeRequired){

            newRandomNumber =  showRandomInteger(min, max, randomGenerator.nextDouble());
            if(!randomNumberSet.contains(newRandomNumber)){
                randomNumberSet.add(newRandomNumber);
                counter++;
            }

        }

        List<Integer> list = new ArrayList<>();
        for(Integer number : (Integer [])randomNumberSet.toArray()){
            list.add(number);
        }

        return list;

    }

}
