package com.masteringintegration.kafka;

import java.util.Random;
public class StockQuote {
  private int quote;
  private String name;


    public StockQuote() {
        String company[] = new String[] { "Microsoft", "IBM", "Oracle", "Accenture", "HPE" };
        Random rand = new Random();

        quote = rand.nextInt(1000);
        name = company[rand.nextInt(5)];

    }
    public int getQuote() {
        return quote;
    }

    public void setQuote(int quote) {
        this.quote = quote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "StockQuote{" +
                "quote=" + quote +
                ", name='" + name + '\'' +
                '}';
    }
}