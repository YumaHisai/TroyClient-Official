package it.md_4.troy.ip;

import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MacAddress {
    public static String get(){
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        NetworkInterface ni = null;
        try {
            ni = NetworkInterface.getByInetAddress(localHost);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        byte[] hardwareAddress = new byte[0];
        try {
            hardwareAddress = ni.getHardwareAddress();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        String[] hexadecimal = new String[hardwareAddress.length];
        for (int i = 0; i < hardwareAddress.length; i++) {
            hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
        }
        String macAddress = String.join("-", hexadecimal);

        return macAddress;
    }

    public static boolean isVerified;
}
