package com.codenjoy.dojo.snake.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.snake.client.handler.Graph;
import com.codenjoy.dojo.snake.client.handler2matrix.MatrixBoard;

import java.sql.Timestamp;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;

        //поиск пути по матрице
//        long time = System.currentTimeMillis();
//        System.out.println(time);
//
//        MatrixBoard graph = new MatrixBoard();
//        graph.createBoard(board);
//
//        time = System.currentTimeMillis();
//        System.out.println(time);
//
//        return graph.startSearchWayWave(board).toString();
        //-----------------------

        Graph graph = new Graph();
        graph.createGraph(board);
        graph.searchWay();
//        System.out.println(graph.toString());

        return Direction.DOWN.toString();
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://104.248.134.118/codenjoy-contest/board/player/xdx0z0ggtav7mo4zk84c?code=225158883438032295",
                new YourSolver(new RandomDice()),
                new Board());
    }

}
