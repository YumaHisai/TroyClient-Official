// 
// Decompiled by Procyon v0.5.36
// 

package it.md_4.troy.com.darkmagician6.eventapi.events;

public abstract class EventStoppable implements Event
{
    private boolean stopped;
    
    protected EventStoppable() {
    }
    
    public void stop() {
        this.stopped = true;
    }
    
    public boolean isStopped() {
        return this.stopped;
    }
}
