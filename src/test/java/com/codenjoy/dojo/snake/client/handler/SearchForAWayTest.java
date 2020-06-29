package com.codenjoy.dojo.snake.client.handler;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchForAWayTest {
    List<Point> array = new ArrayList<>();
    Map<Point, ArrayList<Point>> map = new HashMap<>();

    @Test
    public void waveSearch() {
        Point point1 = new Point(0, 0, ' ');
        Point point2 = new Point(0, 1, ' ');
        Point point3 = new Point(1, 0, ' ');
        Point point4 = new Point(1, 1, '1');

        ArrayList<Point> list1 = new ArrayList<>();
        list1.add(point2);
        list1.add(point3);

        ArrayList<Point> list2 = new ArrayList<>();
        list2.add(point1);
        list2.add(point4);

        ArrayList<Point> list3 = new ArrayList<>();
        list3.add(point1);
        list3.add(point4);

        ArrayList<Point> list4 = new ArrayList<>();
        list4.add(point2);
        list4.add(point3);

        map.put(point1, list1);
        map.put(point2, list2);
        map.put(point3, list3);
        map.put(point4, list4);

        array.add(point1);
        recurs();

//        System.out.println(map.get(array.get(0)));
    }

    void recurs(){
        int size = array.size();
        boolean apple = true;
        for(int j = 0; j < size; j++){
            for(int i = 0; i < map.get(array.get(j)).size(); i++){
                System.out.println(map.get(array.get(j)).get(i).getValue());
                System.out.println(array);
                array.add(map.get(array.get(j)).get(i));
                if(map.get(array.get(j)).get(i).getValue() == '1'){
                    apple = false;
                    System.out.println("изменило на фолс");
                    break;
                }
                System.out.println(apple);
            }
            array.remove(0);
        }
        if(apple){
            recurs();
        } else {
            System.out.println("нашло");
        }
    }
}