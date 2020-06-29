package com.codenjoy.dojo.snake.client.handler;

import com.codenjoy.dojo.snake.client.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    Map<Point, ArrayList<Point>> graph = new HashMap<>();
    Point start;
    Point goodApple;
    List<Point> array = new ArrayList<>();

    public void createConnection(Point from, Point to){
        if(graph.get(from)==null){
            graph.put(from, new ArrayList<>());
        }
        graph.get(from).add(to);
    }

    public void createGraph(Board board) {
        for (int i = 0; i < board.getField().length; i++) {
            for (int j = 0; j < board.getField()[i].length; j++) {
                Point from = new Point(i, j, board.getField()[i][j]);

                //сохраняю поинт головы и яблока
                if(from.getValue() == '☻'){
                    goodApple = from;
                }
                if(from.getValue() == '▼' | from.getValue() == '◄' | from.getValue() == '►'| from.getValue() == '▲'){
                    start = from;
                }
                //-----------------------------

                if (i > 0) {
                    Point to = new Point(i - 1, j, board.getField()[i-1][j]);
                    createConnection(from, to);
                }
                if (i < board.getField().length-1) {
                    Point to = new Point(i + 1, j, board.getField()[i+1][j]);
                    createConnection(from, to);
                }
                if (j > 0) {
                    Point to = new Point(i, j - 1, board.getField()[i][j-1]);
                    createConnection(from, to);
                }
                if (j < board.getField().length-1) {
                    Point to = new Point(i, j + 1, board.getField()[i][j+1]);
                    createConnection(from, to);
                }
            }
        }
    }

    public void searchWay(){
        waveSearch();
    }

    public void waveSearch(){
        start.setSearch(1);
        array.add(start);
        nextWave();
    }

    private void nextWave(){
        System.out.println("зашло в nextWave()");
        int size = array.size();

        System.out.println(size);
        System.out.println(start);
        System.out.println(graph.get(start));


        boolean apple = true;
        for(int j = 0; j < size; j++){
            for(int i = 0; i < graph.get(array.get(j)).size(); i++){
                System.out.println("пошел цикл");
                System.out.println(graph.get(array.get(j)).get(i).getValue());
                System.out.println(array);
                array.add(graph.get(array.get(j)).get(i));
                if(graph.get(array.get(j)).get(i).getValue() == '☻'){
                    apple = false;
                    System.out.println("изменило на фолс");
                    break;
                }
                System.out.println(apple);
            }
            array.remove(0);
        }
        if(apple){
            System.out.println("перевызвался метод");
            nextWave();
        } else {
            System.out.println("нашло");
        }
    }

    public Point getHead() {
        return start;
    }

    public Point getGoodApple() {
        return goodApple;
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
