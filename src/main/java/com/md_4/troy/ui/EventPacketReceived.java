// 
// Decompiled by Procyon v0.5.36
// 

package com.md_4.troy.ui;

import com.md_4.troy.com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.network.Packet;

public class EventPacketReceived extends EventCancellable
{
    private Packet packet;
    
    public EventPacketReceived(final Packet packet) {
        this.packet = packet;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public void setPacket(final Packet packet) {
        this.packet = packet;
    }
}
