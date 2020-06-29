//package com.codenjoy.dojo.snake.client.handler;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class SearchForAWay {
//    Map<Point, ArrayList<Point>> graph;
//    Point start;
//    Point finish;
//
//    List<Point> pointsInSearch = new ArrayList<>();
//
//    public void waveSearch(HashMap<Point, ArrayList<Point>> graph, Point start){
//        this.graph = graph;
//        this.start = start;
//
//        start.setSearch(1);
//        pointsInSearch.add(start);
//        nextWave();
//    }
//
//    private void nextWave(){
//        int size = pointsInSearch.size();
//        boolean apple = true;
//        for(int j = 0; j < size; j++){
//            for(int i = 0; i < graph.get(pointsInSearch.get(j)).size(); i++){
//                System.out.println("пошел цикл");
//                System.out.println(graph.get(pointsInSearch.get(j)).get(i).getValue());
//                System.out.println(pointsInSearch);
//                pointsInSearch.add(graph.get(pointsInSearch.get(j)).get(i));
//                if(graph.get(pointsInSearch.get(j)).get(i).getValue() == '☻'){
//                    apple = false;
//                    System.out.println("изменило на фолс");
//                    break;
//                }
//                System.out.println(apple);
//            }
//            pointsInSearch.remove(0);
//        }
//        if(apple){
//            System.out.println("перевызвался метод");
//            nextWave();
//        } else {
//            System.out.println("нашло");
//        }
//    }
//}
