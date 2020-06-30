package com.codenjoy.dojo.snake.client.handler;

import com.codenjoy.dojo.services.Direction;
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
        graph.clear();
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
            }
        }
        for (int i = 0; i < points.size(); i++){
            Point from = points.get(i);
            graph.put(from, new ArrayList<>());
            System.out.println("первая вершина добавлена: "+from);

            int mainX = from.getX();
            int mainY = from.getY();

            for (int j = 0; j < points.size(); j++){
                int thisX = points.get(j).getX();
                int thisY = points.get(j).getY();

                if(thisX==mainX+1 & thisY==mainY || thisX == mainX-1 & thisY==mainY || thisY == mainY+1 & thisX == mainX || thisY == mainY-1 & thisX == mainX){
                    System.out.println("в нее добавлена связь: "+ points.get(j));
                    graph.get(from).add(points.get(j));
                }
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
//                        System.out.println(graph.get(array.get(j)).get(i));
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
            if(array.size() > 0){
                System.out.println("удаляет елементы из этого массива");
                System.out.println(array);
                System.out.println("вот что от него осталось");
                array.remove(0);
                System.out.println(array);
            }
        }

        if(apple){
            System.out.println("рекурсия в nextWave");
            System.out.println(array);
            nextWave();
        } else {
            System.out.println("нашло");
            waveBack();
        }
    }

    private Direction waveBack(){
        Direction direction = Direction.STOP;
        System.out.println("в функции waveBack");
        if(applePoint.getSearch() != 1){
            search--;
            System.out.println("search: "+search);
            System.out.println("текущая вершина алгоритма возврата: "+applePoint);
            for(int j = 0; j < 4; j++){
                System.out.println("вершина связанная с текущей в графе: "+graph.get(applePoint).get(j));
                if(graph.get(applePoint).get(j).getSearch() == search){
                    applePoint = graph.get(applePoint).get(j);
                    if(graph.get(applePoint).get(j).getSearch() == 1){
                        System.out.println("вернулось к голове");
                        break;
                    } else {
                        System.out.println("рекурснулся метод возвращения");
                        waveBack();
                    }
                }
            }
        } else {
            System.out.println("search: "+search);
            System.out.println("текущая вершина алгоритма возврата: "+applePoint);
            System.out.println("не зашел в иф");

        }
        return direction;
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
