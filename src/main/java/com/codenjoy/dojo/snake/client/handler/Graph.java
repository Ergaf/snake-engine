package com.codenjoy.dojo.snake.client.handler;

import com.codenjoy.dojo.snake.client.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    Map<Point, ArrayList<Point>> graph = new HashMap<>();
    Point start;
    Point applePoint;;
    List<Point> array = new ArrayList<>();
    int search;

    public void createConnection(Point from, Point to){
        if(graph.get(from)==null){
            graph.put(from, new ArrayList<>());
        }
        graph.get(from).add(to);
    }

    public void createGraph(Board board) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < board.getField().length; i++) {
            for (int j = 0; j < board.getField()[i].length; j++) {
                Point from = new Point(i, j, board.getField()[i][j]);
                points.add(from);

                //сохраняю поинт головы
                if(from.getValue() == '▼' | from.getValue() == '◄' | from.getValue() == '►'| from.getValue() == '▲'){
                    start = from;
                }
                //-----------------------------

//                if (i > 0) {
//                    Point to = new Point(i - 1, j, board.getField()[i-1][j]);
//                    createConnection(from, to);
//                }
//                if (i < board.getField().length-1) {
//                    Point to = new Point(i + 1, j, board.getField()[i+1][j]);
//                    createConnection(from, to);
//                }
//                if (j > 0) {
//                    Point to = new Point(i, j - 1, board.getField()[i][j-1]);
//                    createConnection(from, to);
//                }
//                if (j < board.getField().length-1) {
//                    Point to = new Point(i, j + 1, board.getField()[i][j+1]);
//                    createConnection(from, to);
//                }
            }
        }

        for (int i = 0; i < board.getField().length; i++){
            for (int j = 0; j < board.getField()[i].length; j++){

            }
        }


    }

    public void searchWay(){
        waveSearch();
    }

    //Поиск в Ширину--------------------------
    public void waveSearch(){
        if(start != null){
            array.clear();
            search = 1;
            start.setSearch(search);
            array.add(start);
            nextWave();
        }
    }

    private void nextWave(){
        int size = array.size();
        search++;

        int toDelete = 0;

        boolean apple = true;
        for(int j = 0; j < size; j++){
            for(int i = 0; i < graph.get(array.get(j)).size(); i++){
                if(graph.get(array.get(j)).get(i).getValue() == ' ' | graph.get(array.get(j)).get(i).getValue() == '☺'){
                    if(graph.get(array.get(j)).get(i).getSearch() == 0){
                        graph.get(array.get(j)).get(i).setSearch(search);
                        System.out.println(graph.get(array.get(j)).get(i));
                        array.add(graph.get(array.get(j)).get(i));
                        if(graph.get(array.get(j)).get(i).getValue() == '☺'){
                            apple = false;
                            applePoint = graph.get(array.get(j)).get(i);
                            System.out.println(graph.get(applePoint));

                            //костыли ебаные
                            break;
                        }
                    }
                }
                if(i >= 3){
                    toDelete++;
                }
            }
            if(!apple){
                break;
            }
        }

        for(int i = toDelete; i > 0; i--){
            array.remove(0);
        }

        if(apple){
            System.out.println(array);
            nextWave();
        } else {
            System.out.println("нашло");
            waveBack();
        }
    }

    private void waveBack(){
        System.out.println("в функции waveBack");
        System.out.println(graph);

        System.out.println("ЯБЛОКО В БЕКЕ");
        System.out.println(applePoint);
        for(int j = 0; j < 4; j++){
            System.out.println(graph.get(applePoint).get(j));
            if(graph.get(applePoint).get(j).getSearch() == search-1){
                applePoint = graph.get(applePoint).get(j);
                if(graph.get(applePoint).get(j).getSearch() == 1){
                    System.out.println("вернулось к голове");
                } else {
                    System.out.println("рекурснулся метод возвращения");
                    System.out.println(applePoint);
                    waveBack();
                }
            }
        }
    }
    //-------------------------------------------

    public Point getHead() {
        return start;
    }

    public Point getGoodApple() {
        return applePoint;
    }

    public Map<Point, ArrayList<Point>> getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        return "size= " + graph.size() +
                " graph= " + graph +
                ' ';
    }
}
