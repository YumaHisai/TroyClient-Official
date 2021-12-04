// 
// Decompiled by Procyon v0.5.36
// 

package com.md_4.troy.com.darkmagician6.eventapi.events.callables;


import com.md_4.troy.com.darkmagician6.eventapi.events.Event;
import com.md_4.troy.com.darkmagician6.eventapi.events.Typed;

public abstract class EventTyped implements Event, Typed
{
    private final byte type;
    
    protected EventTyped(final byte eventType) {
        this.type = eventType;
    }
    
    @Override
    public byte getType() {
        return this.type;
    }
}
