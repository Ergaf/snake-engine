package com.codenjoy.dojo.snake.client.handler2matrix;

import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.snake.client.Board;

import java.util.ArrayList;
import java.util.List;

public class MatrixBoard {
    int[][] boardM;

    List<PointM> pointsForSearchWave = new ArrayList<>();
    int w = 1;
    PointM applePoint;
    PointM pointForBack;
//    public Direction direction;

    public void createBoard(Board board) {
        boardM = new int[board.getField().length][board.getField().length];
        for (int i = 0; i < board.getField().length; i++) {
            for (int j = 0; j < board.getField()[i].length; j++) {
                if (board.getField()[i][j] == ' ') {
                    boardM[i][j] = 0;
                } else {
                    boardM[i][j] = -1;
                }
                if (board.getField()[i][j] == '☼') {
                    boardM[i][j] = -3;
                }
                if (board.getField()[i][j] == '☻') {
                    boardM[i][j] = -2;
                }
                if (board.getField()[i][j] == '☺') {
                    boardM[i][j] = -9;
                }
                if (board.getField()[i][j] == '▼' | board.getField()[i][j] == '◄' | board.getField()[i][j] == '►' | board.getField()[i][j] == '▲') {
                    boardM[i][j] = -7;
                }
            }
        }
    }

    public void seeBoardM() {
        for (int i = 0; i < boardM.length; i++) {
            for (int j = 0; j < boardM[i].length; j++) {
                System.out.print(boardM[i][j]);
            }
            System.out.println();
        }
    }

    public Direction startSearchWayWave(Board board) {

        PointM start = new PointM();
        String direction = " ";

        System.out.println(board.getApples().get(0));

        if (board.getHead() != null) {
            start.x = board.getApples().get(0).getX();
            start.y = board.getApples().get(0).getY();

            pointsForSearchWave.add(start);

            int x = start.x;
            int y = start.y;

            boardM[x][y] = w;
            for (int i = 0; i < 4; i++) {
                if (boardM[x + 1][y] == 0) {
                    boardM[x + 1][y] = w + 1;
                    PointM point = new PointM();
                    point.x = x + 1;
                    point.y = y;
                    pointsForSearchWave.add(point);
                }
                if (boardM[x - 1][y] == 0) {
                    boardM[x - 1][y] = w + 1;
                    PointM point = new PointM();
                    point.x = x - 1;
                    point.y = y;
                    pointsForSearchWave.add(point);
                }
                if (boardM[x][y + 1] == 0) {
                    boardM[x][y + 1] = w + 1;
                    PointM point = new PointM();
                    point.x = x;
                    point.y = y + 1;
                    pointsForSearchWave.add(point);
                }
                if (boardM[x][y - 1] == 0) {
                    boardM[x][y - 1] = w + 1;
                    PointM point = new PointM();
                    point.x = x;
                    point.y = y + 1;
                    pointsForSearchWave.add(point);
                }
            }
            System.out.println(pointsForSearchWave);
            w++;
            nextWave();

            return doNext();
        }
        return Direction.UP;
    }

    void nextWave() {
        int pointss = pointsForSearchWave.size();
        int apple = 0;

        try{
            for (int i = 0; i < pointss; i++) {
                for (int j = 0; j < 4; j++) {
                    if (boardM[pointsForSearchWave.get(i).x][pointsForSearchWave.get(i).y + 1] == 0) {
                        boardM[pointsForSearchWave.get(i).x][pointsForSearchWave.get(i).y + 1] = w + 1;
                        PointM point = new PointM();
                        if(pointsForSearchWave.get(i).y < 15){
                            point.x = pointsForSearchWave.get(i).x;
                            point.y = pointsForSearchWave.get(i).y + 1;
                            pointsForSearchWave.add(point);
                        }
                    }
                    else if (boardM[pointsForSearchWave.get(i).x][pointsForSearchWave.get(i).y + 1] == -7) {
                        PointM point = new PointM();
                        if(pointsForSearchWave.get(i).y < 15){
                            point.x = pointsForSearchWave.get(i).x;
                            point.y = pointsForSearchWave.get(i).y + 1;
                            pointsForSearchWave.add(point);
                        }
                        apple = 1;
                        applePoint = point;
                        boardM[applePoint.x][applePoint.y] = w + 1;
                    }

                    if (boardM[pointsForSearchWave.get(i).x][pointsForSearchWave.get(i).y - 1] == 0) {
                        boardM[pointsForSearchWave.get(i).x][pointsForSearchWave.get(i).y - 1] = w + 1;
                        PointM point = new PointM();
                        point.x = pointsForSearchWave.get(i).x;
                        point.y = pointsForSearchWave.get(i).y - 1;
                        pointsForSearchWave.add(point);
                    }
                    else if (boardM[pointsForSearchWave.get(i).x][pointsForSearchWave.get(i).y - 1] == -7) {
                        PointM point = new PointM();
                        point.x = pointsForSearchWave.get(i).x;
                        point.y = pointsForSearchWave.get(i).y - 1;
                        pointsForSearchWave.add(point);
                        apple = 1;
                        applePoint = point;
                        boardM[applePoint.x][applePoint.y] = w + 1;
                    }

                    if (boardM[pointsForSearchWave.get(i).x + 1][pointsForSearchWave.get(i).y] == 0) {
                        boardM[pointsForSearchWave.get(i).x + 1][pointsForSearchWave.get(i).y] = w + 1;
                        PointM point = new PointM();
                        if(pointsForSearchWave.get(i).x + 1 < 15){
                            point.x = pointsForSearchWave.get(i).x + 1;
                            point.y = pointsForSearchWave.get(i).y;
                            pointsForSearchWave.add(point);
                        }
                    }
                    else if (boardM[pointsForSearchWave.get(i).x + 1][pointsForSearchWave.get(i).y] == -7) {
                        PointM point = new PointM();
                        if(pointsForSearchWave.get(i).x + 1 < 15){
                            point.x = pointsForSearchWave.get(i).x + 1;
                            point.y = pointsForSearchWave.get(i).y;
                            pointsForSearchWave.add(point);
                        }
                        apple = 1;
                        applePoint = point;
                        boardM[applePoint.x][applePoint.y] = w + 1;
                    }

                    if (boardM[pointsForSearchWave.get(i).x - 1][pointsForSearchWave.get(i).y] == 0) {
                        boardM[pointsForSearchWave.get(i).x - 1][pointsForSearchWave.get(i).y] = w + 1;
                        PointM point = new PointM();
                        point.x = pointsForSearchWave.get(i).x - 1;
                        point.y = pointsForSearchWave.get(i).y;
                        pointsForSearchWave.add(point);
                    }
                    else if (boardM[pointsForSearchWave.get(i).x - 1][pointsForSearchWave.get(i).y] == -7) {
                        PointM point = new PointM();
                        point.x = pointsForSearchWave.get(i).x - 1;
                        point.y = pointsForSearchWave.get(i).y;
                        pointsForSearchWave.add(point);
                        apple = 1;
                        applePoint = point;
                        boardM[applePoint.x][applePoint.y] = w + 1;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            apple = 1;
        }
        w++;

        if (apple != 1) {
            nextWave();
        } else {
            System.out.println("волна долша до головы змеи.");
        }
    }

    Direction doNext(){
        try{
            w--;
            System.out.println(boardM[applePoint.x][applePoint.y]);
            System.out.println(w);

            System.out.println(boardM[applePoint.x+1][applePoint.y]);
            System.out.println(boardM[applePoint.x-1][applePoint.y]);
            System.out.println(boardM[applePoint.x][applePoint.y+1]);
            System.out.println(boardM[applePoint.x][applePoint.y-1]);

            if(boardM[applePoint.x+1][applePoint.y] == w | boardM[applePoint.x+1][applePoint.y] == 1){
                return Direction.RIGHT;
            }
            if(boardM[applePoint.x-1][applePoint.y] == w | boardM[applePoint.x-1][applePoint.y] == 1){
                return Direction.LEFT;
            }
            if(boardM[applePoint.x][applePoint.y+1] == w | boardM[applePoint.x][applePoint.y+1] == 1){
                return Direction.UP;
            }
            if(boardM[applePoint.x][applePoint.y-1] == w | boardM[applePoint.x][applePoint.y-1] == 1){
                return Direction.DOWN;
            } else {
                return Direction.STOP;
            }
        } catch (NullPointerException e){
            return Direction.STOP;
        }
    }
}