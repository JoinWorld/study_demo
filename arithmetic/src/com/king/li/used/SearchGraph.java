package com.king.li.used;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 实现图-时间复杂度O(V+E)
 * 使用队列
 * 例子-查找经销商问题
 *
 * @author li
 * @create 2021-05-30-11:27
 */
public class SearchGraph {
    public static void main(String[] args) {
        Map<String, String[]> seller_graph = new HashMap<>();
        seller_graph.put("you", new String[]{"alice", "bob", "claire"});
        seller_graph.put("bob", new String[]{"anuj", "peggy"});
        seller_graph.put("alice", new String[]{"peggy"});
        seller_graph.put("claire", new String[]{"thom", "jonny"});
        seller_graph.put("anuj", null);
        seller_graph.put("peggy", null);
        seller_graph.put("thom", null);
        seller_graph.put("jonny", null);

        System.out.println(search_seller(seller_graph));
    }


    public static String search_seller(Map<String, String[]> seller_graph) {
        List<String> recordList = new ArrayList<>();
        Queue<String> searchQueue = new ConcurrentLinkedQueue<>();
        searchQueue.add("you");
        String name = searchQueue.poll();
        while (!"".equals(name) && name != null) {
            //判断是否已经检查过了
            if(recordList.indexOf(name) >= 0){
                name = searchQueue.poll();
                continue;
            }

            if (isSeller(name)) { //判断是否为经销商
                return name;
            }

            //防止重复
            recordList.add(name);

            //查看是否有其他联系人
            String[] otherNames = seller_graph.get(name);
            if (otherNames != null) {
                for (int i = 0; i < otherNames.length; i++) {
                    searchQueue.add(otherNames[i]);
                }
            }

            name = searchQueue.poll();
        }

        return null;
    }

    /**
     * 是否为经销商
     *
     * @param name
     * @return
     */
    public static boolean isSeller(String name) {
        return 'm' == name.charAt(name.length() - 1);
    }
}
