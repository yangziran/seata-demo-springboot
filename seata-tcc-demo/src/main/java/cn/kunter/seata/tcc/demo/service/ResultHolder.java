package cn.kunter.seata.tcc.demo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Result Holder
 * @author nature
 * @version 1.0 2020/6/18
 */
public class ResultHolder {

    private static Map<String, String> orderResults = new ConcurrentHashMap<>();
    private static Map<String, String> storageResults = new ConcurrentHashMap<>();
    private static Map<String, String> payResults = new ConcurrentHashMap<>();


    public static String getOrderResult(String xid) {
        return orderResults.get(xid);
    }

    public static void setOrderResult(String xid, String result) {
        orderResults.put(xid, result);
    }

    public static String getStorageResult(String xid) {
        return storageResults.get(xid);
    }

    public static void setStorageResult(String xid, String result) {
        storageResults.put(xid, result);
    }

    public static String getPayResult(String xid) {
        return payResults.get(xid);
    }

    public static void setPayResult(String xid, String result) {
        payResults.put(xid, result);
    }

}
