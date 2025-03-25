package com.adhiraj.dsalgo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// LC-2034
public class StockPriceFluctuation {
    public static void main(String[] args) {

    }
}

class StockPrice {

    Map<Integer, Stock> priceCache = new HashMap<>();
    TreeSet<Stock> priceBst = new TreeSet<>(new StockComparator());
    Stock currStockInst = null;

    public StockPrice() {

    }

    public void update(int timestamp, int price) {
        Stock stock = priceCache.get(timestamp);
        if (stock == null) {
            stock = new Stock(timestamp, price);
            priceCache.put(timestamp, stock);
            priceBst.add(stock);
        } else {
            priceBst.remove(stock);
            stock.price = price;
            priceBst.add(stock);
        }
        if (currStockInst == null || currStockInst.ts < stock.ts) currStockInst = stock;
    }

    public int current() {
        return currStockInst.price;
    }

    public int maximum() {
        return priceBst.last().price;
    }

    public int minimum() {
        return priceBst.first().price;
    }
}

class StockComparator implements Comparator<Stock> {
    public int compare(Stock a, Stock b) {
        int cmp = Integer.compare(a.price, b.price);
        if (cmp == 0) cmp = Integer.compare(a.ts, b.ts);
        return cmp;
    }
}

class Stock {
    int ts;
    int price;

    Stock(int ts, int price) {
        this.ts = ts;
        this.price = price;
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
