/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.bootstrap.ChannelFactory
 *  io.netty.channel.socket.oio.OioSocketChannel
 */
package it.md_4.troy.ui.guis.proxy;

import io.netty.bootstrap.ChannelFactory;
import io.netty.channel.socket.oio.OioSocketChannel;
import java.lang.reflect.Method;
import java.net.Proxy;
import java.net.Socket;

public class ProxyUtils
implements ChannelFactory<OioSocketChannel> {
    private /* synthetic */ Proxy proxy;

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public ProxyUtils(Proxy proxy) {
        this.proxy = proxy;
    }

    public OioSocketChannel newChannel() {
        if (this.proxy == null || this.proxy == Proxy.NO_PROXY) {
            return new OioSocketChannel(new Socket(Proxy.NO_PROXY));
        }
        Socket socket = new Socket(this.proxy);
        try {
            Method method = socket.getClass().getDeclaredMethod("getImpl", new Class[0]);
            method.setAccessible(true);
            Object object = method.invoke(socket, new Object[0]);
            method = object.getClass().getDeclaredMethod("setV4", new Class[0]);
            method.setAccessible(true);
            method.invoke(object, new Object[0]);
            return new OioSocketChannel(socket);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public Proxy getProxy() {
        return this.proxy;
    }
}

