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
    Point currentPoint;;
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
//                if(from.getValue() == '☺'){
//                    start = from;
//                }
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
    public Direction waveSearch(){
        if(start != null){
            array.clear();
            search = 1;
            start.setSearch(search);
            array.add(start);
            return nextWave();
        }
        return null;
    }

    private Direction nextWave(){
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
                            currentPoint = graph.get(array.get(j)).get(i);
                            currentPoint.setToApple(1);
                            System.out.println(graph.get(currentPoint));
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
        }
        if(!apple){
            System.out.println("нашло");
            System.out.println("вроде как должно вернуть не стоп в методе nextWave");
            return waveBack();
        }
        System.out.println("вернуло STOP в методе nextWave");
        return Direction.STOP;
    }

    private Direction waveBack(){
        System.out.println("в функции waveBack");
        boolean isRecurs = false;
        if(currentPoint.getSearch() != 1){
            search--;
            System.out.println("search: "+search);
            System.out.println("текущая вершина алгоритма возврата: "+ currentPoint);
            for(int j = 0; j < 4; j++){
                System.out.println("вершина связанная с текущей в графе: "+graph.get(currentPoint).get(j));
                if(graph.get(currentPoint).get(j).getSearch() == search){
                    currentPoint = graph.get(currentPoint).get(j);
                    currentPoint.setToApple(currentPoint.getToApple()+1);
                    if(graph.get(currentPoint).get(j).getSearch() == 1){
                        System.out.println("вернулось к голове");
                        System.out.println("текущая вершина алгоритма возврата: "+ currentPoint);
//                        return toGo();
                    } else {
                        isRecurs = true;
                    }
                }
            }
        }
        if(currentPoint.getSearch() == 1){
            return toGo();
        }
        if(isRecurs){
            System.out.println("рекурснулся метод возвращения");
            waveBack();
        }
        System.out.println("вернуло STOP в методе waveBack");
        return Direction.STOP;
    }

    private Direction toGo(){
        System.out.println("search: "+search);
        System.out.println("текущая вершина алгоритма возврата: "+ currentPoint);
        System.out.println("не зашел в иф");
        for(int j = 0; j < 4; j++){
            if(graph.get(currentPoint).get(j).toApple == 1){
                System.out.println("нашло куда повернуть башку!");
                //выдача окончательного решения куда повернуть
                int cX = currentPoint.getX();
                int cY = currentPoint.getY();
                int toX = graph.get(currentPoint).get(j).getX();
                int toY = graph.get(currentPoint).get(j).getY();

                if(cX == toX+1 & cY == toY){
                    System.out.println("должно вернуть DOWN");
                    return Direction.DOWN;
                }
                if(cX == toX-1 & cY == toY){
                    System.out.println("должно вернуть UP");
                    return Direction.UP;
                }
                if(cY == toY+1 & cX == toX){
                    System.out.println("должно вернуть RIGHT");
                    return Direction.RIGHT;
                }
                if(cY == toY-1 & cX == toX){
                    System.out.println("должно вернуть LEFT");
                    return Direction.LEFT;
                }
                //--------------------------------------------
            }
        }
        System.out.println("вернуло STOP в методе toGo");
        return Direction.STOP;
    }
    //-------------------------------------------

    public Point getHead() {
        return start;
    }

    public Point getGoodApple() {
        return currentPoint;
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
