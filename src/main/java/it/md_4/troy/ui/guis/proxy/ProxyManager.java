/*
 * Decompiled with CFR 0.150.
 */
package it.md_4.troy.ui.guis.proxy;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class ProxyManager {
    public static volatile /* synthetic */ Proxy proxy;

    public static void setProxy(Proxy proxy) {
        ProxyManager.proxy = proxy;
    }

    public static Proxy getProxy() {
        return proxy;
    }

    public static Proxy getProxyFromString(String string) {
        return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(string.split(":")[0], (int)Integer.valueOf(string.split(":")[1])));
    }
}

