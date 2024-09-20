package org.jeasy.rules.tutorials.airco;

import org.jeasy.rules.api.Facts;

public class BConsumer implements Consumer<Facts>{
    @Override
    public void accept(Facts facts) {
        System.out.println("consume B result");
    }
}
