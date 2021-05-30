package com.king.li.used;

import java.util.HashMap;
import java.util.Map;

/**
 * 寻找加权最短路径
 * 使用狄克斯特拉算法，加权为证，并且没有循环路径
 * @author li
 * @create 2021-05-30-23:04
 */
public class FindLowestCostNode {
    public static void main(String[] args) {
        //初始化节点和权重
        Map<String, Map<String,Integer>> graph = new HashMap<>();
        HashMap<String, Integer> node = new HashMap<>();
        node.put("A",6);
        node.put("B",2);
        graph.put("start",node);
        node = new HashMap<>();
        node.put("end",1);
        graph.put("A",node);
        node = new HashMap<>();
        node.put("A",3);
        node.put("end",5);
        graph.put("B",node);
        graph.put("end",null);


    }

    public void findLowestCostNode(Map<String, Map<String,Integer>> graph){

    }
}
