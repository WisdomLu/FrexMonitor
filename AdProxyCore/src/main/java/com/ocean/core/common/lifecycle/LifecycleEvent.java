package com.ocean.core.common.lifecycle;


public final class LifecycleEvent {
 
    private LifecycleState state;
 
    public LifecycleEvent(LifecycleState state) {
        this.state = state;
    }
 
    /**
     * @return the state
     */
    public LifecycleState getState() {
        return state;
    }
 
}