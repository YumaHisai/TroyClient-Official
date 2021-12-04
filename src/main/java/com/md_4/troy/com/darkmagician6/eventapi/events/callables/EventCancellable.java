// 
// Decompiled by Procyon v0.5.36
// 

package com.md_4.troy.com.darkmagician6.eventapi.events.callables;

import com.md_4.troy.com.darkmagician6.eventapi.events.Cancellable;
import com.md_4.troy.com.darkmagician6.eventapi.events.Event;

public abstract class EventCancellable implements Event, Cancellable
{
    private boolean cancelled;
    
    protected EventCancellable() {
    }
    
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    @Override
    public void setCancelled(final boolean state) {
        this.cancelled = state;
    }
}
