package com.codenjoy.dojo.snake.client.handler;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchForAWay {
    HashMap<Point, ArrayList<Point>> graph;
    Point start;
    Point finish;

    List<Point> pointsInSearch = new ArrayList<>();

    public void waveSearch(){
        start.setSearch(1);
        pointsInSearch.add(start);
        nextWave();
    }

    private void nextWave(){
        int size = pointsInSearch.size();
        int inc = 0;
        boolean isApple = false;
        while(size > inc){
            System.out.println(pointsInSearch.get(inc));
            System.out.println(graph.get(pointsInSearch.get(inc)));
            for(int i = 0; i < graph.get(pointsInSearch.get(inc)).size(); i++){
                System.out.println("хоть раз сработало");
                System.out.println(pointsInSearch.toString());
                //устанавливаем значение search равное на 1 больше
                graph.get(pointsInSearch.get(inc)).get(i).setSearch(pointsInSearch.get(inc).getSearch()+1);
                if(graph.get(pointsInSearch.get(inc)).get(i).getValue() == '☻'){
                    isApple = true;
                }
                //добавляем поинт в массив следующего поиска
                pointsInSearch.add(graph.get(pointsInSearch.get(inc)).get(i));
            }
            //по идее должно удалять предыдущие значение
            pointsInSearch.remove(0);
            System.out.println(pointsInSearch.toString());
            inc++;
        }
        if(!isApple){
            System.out.println("хоть раз перевызвался метод nextWave");
            nextWave();
        } else {
            System.out.println("нашло яблоко!");
        }
    }
}
