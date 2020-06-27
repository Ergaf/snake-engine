package com.codenjoy.dojo.snake.client.handler;

import com.codenjoy.dojo.snake.client.Board;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    HashMap<Point, ArrayList<Point>> graph = new HashMap<>();
    Point head;
    Point goodApple;

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
                    head = from;
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
        SearchForAWay searchForAWay = new SearchForAWay();
        searchForAWay.graph = graph;
        searchForAWay.start = head;
        searchForAWay.finish = goodApple;

        searchForAWay.waveSearch();
    }

    public Point getHead() {
        return head;
    }

    public Point getGoodApple() {
        return goodApple;
    }

    public HashMap<Point, ArrayList<Point>> getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        return "size= " + graph.size() +
                " graph= " + graph +
                ' ';
    }
}
