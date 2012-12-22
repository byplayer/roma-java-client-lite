package com.rakuten.rit.roma.romac4j;

import java.util.concurrent.TimeoutException;

import com.rakuten.rit.roma.romac4j.pool.Connection;

public class StringReceiver extends Receiver {

    String str;

    @Override
    public void receive(Connection con) throws TimeoutException {
        str = con.readLine();
    }

    public String toString() {
        return str;
    }
}
