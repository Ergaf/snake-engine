package com.codenjoy.dojo.snake.client.handler;

import com.codenjoy.dojo.services.Direction;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SearchForAWay {
    HashMap<Point, ArrayList<Point>> graph;
    Point start;
    Point finish;
    int d;

    Set<Point> keySet;

    List<Point> pointsInSearch = new ArrayList<>();


    public Direction waveSearch(){

        assert graph != null;
        keySet = graph.keySet();

        d = 1;
        if(start != null){
            for(int i = 0; i < graph.get(start).size(); i++){
                if(graph.get(start).get(i).getValue() == ' ' | graph.get(start).get(i).getValue() == '☻'){
                    graph.get(start).get(i).setSearch(d);

                    System.out.println(graph.get(graph.get(start).get(i)));
                }
            }
            nextSearchWave();
        }

        return Direction.DOWN;
    }

    private void nextSearchWave(){
//        for(int i = 0; i < pointsInSearch.size(); i++){
//
//            for(int j = 0; j < keySet.size(); j++){
//
//            }
//
//            for(int j = 0; j < pointsInSearch.size(); j++){
//
//            }
//        }
    }
}
