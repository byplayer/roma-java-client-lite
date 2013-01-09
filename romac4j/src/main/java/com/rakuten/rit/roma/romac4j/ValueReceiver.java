package com.rakuten.rit.roma.romac4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

import com.rakuten.rit.roma.romac4j.pool.Connection;

public class ValueReceiver extends Receiver {
    protected static Logger log = Logger.getLogger(ValueReceiver.class
            .getName());
    String str;
    byte[] value;

    @Override
    public void receive(Connection con) throws TimeoutException, IOException {
        int len = 0;
        str = con.readLine();
        if(str == null){
           throw new IOException();
        }
        log.debug("str: " + str);
        try {
            String[] header = str.split(" ");
            if (header.length >= 4) {
                len = Integer.valueOf(header[3]);
            } else {
                len = Integer.parseInt(str);
            }
        } catch (NumberFormatException e) {
            log.error("Error: NumberFormatException");
            throw new IOException(e);
        }

        if (len > 0) {
            log.debug("receive: len > 0");
            value = con.readValue(len);
        } else {
            log.debug("receive: len == 0");
            value = null;
        }
    }

    public byte[] getValue() {
        return value;
    }

    public int getCasid() {
        String[] header = str.split(" ");
        int len = 0;
        if (header.length == 5) {
            len = Integer.valueOf(header[4]);
        }
        return len;
    }
}
