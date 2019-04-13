package com.ocean.core.common.lifecycle;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractLifecycle implements ILifecycle {
	 
    private List<ILifecycleListener> listeners = new CopyOnWriteArrayList<ILifecycleListener>();
 
    /**
     * state 代表当前生命周期状态
     */
    private LifecycleState state = LifecycleState.NEW;
 
    /*
     * @see ILifecycle#init()
     */
    @Override
    public final synchronized void init() throws LifecycleException {
        if (state != LifecycleState.NEW) {
            return;
        }
 
        setStateAndFireEvent(LifecycleState.INITIALIZING);
        try {
            init0();
        } catch (Throwable t) {
            setStateAndFireEvent(LifecycleState.FAILED);
            if (t instanceof LifecycleException) {
                throw (LifecycleException) t;
            } else {
                throw new LifecycleException(formatString("Failed to initialize {0}, Error Msg: {1}", toString(), t.getMessage()));
            }
        }
        setStateAndFireEvent(LifecycleState.INITIALIZED);
    }
 
    protected abstract void init0() throws LifecycleException;
 
    /*
     * @see ILifecycle#start()
     */
    @Override
    public final synchronized void start() throws LifecycleException {
        if (state == LifecycleState.NEW) {
            init();
        }
 
        if (state != LifecycleState.INITIALIZED) {
            return;
        }
 
        setStateAndFireEvent(LifecycleState.STARTING);
        try {
            start0();
        } catch (Throwable t) {
            setStateAndFireEvent(LifecycleState.FAILED);
            if (t instanceof LifecycleException) {
                throw (LifecycleException) t;
            } else {
                throw new LifecycleException(formatString("Failed to start {0}, Error Msg: {1}",
                        toString(), t.getMessage()));
            }
        }
        setStateAndFireEvent(LifecycleState.STARTED);
    }
 
    protected abstract void start0() throws LifecycleException;
 
    /*
     * @see ILifecycle#suspend()
     */
    @Override
    public final synchronized void suspend() throws LifecycleException {
        if (state == LifecycleState.SUSPENDING || state == LifecycleState.SUSPENDED) {
            return;
        }
 
        if (state != LifecycleState.STARTED) {
            return;
        }
 
        setStateAndFireEvent(LifecycleState.SUSPENDING);
        try {
            suspend0();
        } catch (Throwable t) {
            setStateAndFireEvent(LifecycleState.FAILED);
            if (t instanceof LifecycleException) {
                throw (LifecycleException) t;
            } else {
                throw new LifecycleException(formatString("Failed to suspend {0}, Error Msg: {1}",
                        toString(), t.getMessage()));
            }
        }
        setStateAndFireEvent(LifecycleState.SUSPENDED);
    }
 
    protected abstract void suspend0() throws LifecycleException;
 
    /*
     * @see ILifecycle#resume()
     */
    @Override
    public final synchronized void resume() throws LifecycleException {
        if (state != LifecycleState.SUSPENDED) {
            return;
        }
 
        setStateAndFireEvent(LifecycleState.RESUMING);
        try {
            resume0();
        } catch (Throwable t) {
            setStateAndFireEvent(LifecycleState.FAILED);
            if (t instanceof LifecycleException) {
                throw (LifecycleException) t;
            } else {
                throw new LifecycleException(formatString("Failed to resume {0}, Error Msg: {1}",
                        toString(), t.getMessage()));
            }
        }
        setStateAndFireEvent(LifecycleState.RESUMED);
    }
 
    protected abstract void resume0() throws LifecycleException;
 
    /*
     * @see ILifecycle#destroy()
     */
    @Override
    public final synchronized void destroy() throws LifecycleException {
        if (state == LifecycleState.DESTROYING || state == LifecycleState.DESTROYED) {
            return;
        }
 
        setStateAndFireEvent(LifecycleState.DESTROYING);
        try {
            destroy0();
        } catch (Throwable t) {
            setStateAndFireEvent(LifecycleState.FAILED);
            if (t instanceof LifecycleException) {
                throw (LifecycleException) t;
            } else {
                throw new LifecycleException(formatString("Failed to destroy {0}, Error Msg: {1}",
                        toString(), t.getMessage()));
            }
        }
        setStateAndFireEvent(LifecycleState.DESTROYED);
    }
 
    protected abstract void destroy0() throws LifecycleException;
 
    /*
     * @see
     * ILifecycle#addLifecycleListener(ILifecycleListener)
     */
    @Override
    public void addLifecycleListener(ILifecycleListener listener) {
        listeners.add(listener);
    }
 
    /*
     * @see
     * ILifecycle#removeLifecycleListener(ILifecycleListener)
     */
    @Override
    public void removeLifecycleListener(ILifecycleListener listener) {
        listeners.remove(listener);
    }
 
    private void fireLifecycleEvent(LifecycleEvent event) {
        for (Iterator<ILifecycleListener> it = listeners.iterator(); it.hasNext();) {
            ILifecycleListener listener = it.next();
            listener.lifecycleEvent(event);
        }
    }
 
    protected synchronized LifecycleState getState() {
        return state;
    }
 
    private synchronized void setStateAndFireEvent(LifecycleState newState) throws LifecycleException {
        state = newState;
        fireLifecycleEvent(new LifecycleEvent(state));
    }
 
    private String formatString(String pattern, Object... arguments) {
        return MessageFormat.format(pattern, arguments);
    }
 
    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
