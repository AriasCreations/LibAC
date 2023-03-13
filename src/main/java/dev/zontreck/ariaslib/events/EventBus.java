package dev.zontreck.ariaslib.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import dev.zontreck.ariaslib.events.annotations.Subscribe;
import dev.zontreck.ariaslib.exceptions.EventRegistrationException;

public class EventBus 
{
    public static final EventBus BUS = new EventBus();
    public EventsListenerList listeners = new EventsListenerList();
    /**
     * METHODS MUST BE STATIC!!!
     * @param clazz
     */
    public void register(Class<?> clazz)
    {
        Arrays.stream(clazz.getMethods())
                .filter(m->Modifier.isStatic(m.getModifiers()))
                .filter(m->m.isAnnotationPresent(Subscribe.class))
                .forEach(m-> {
                    try {
                        registerListeners(clazz, m, m);
                    } catch (EventRegistrationException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void registerListeners(final Object target, final Method method, final Method real) throws EventRegistrationException {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if(parameterTypes.length != 1)
        {
            throw new EventRegistrationException("Method "+method+" has an @Subscribe annotation but must have only 1 argument, no more and no less");
        }

        Class<?> eventType = parameterTypes[0];

        if(!Event.class.isAssignableFrom(eventType))
        {
            throw new EventRegistrationException("Event ["+eventType+"] is not a subtype of ["+Event.class.getName()+"]");
        } 


        register(eventType, target, real);
    }

    private void register(Class<?> eventType, Object target, Method real) {
        listeners.addEventMethod(eventType, real, real.getDeclaringClass());
    }

    /**
     * Posts an event to the event bus
     * @param event
     * @return True if the event was cancelled.
     */
    public boolean post(Event event)
    {
        boolean cancelled = false;
        for (Map.Entry<String, List<EventContainer>> entry : listeners.events.entrySet()) {
            Class<?> eventClazz;
            try {
                eventClazz = Class.forName(entry.getKey());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            List<EventContainer> containers = entry.getValue();

            if(eventClazz.isInstance(Event.class))
            {
                for (EventContainer eventContainer : containers) {
                    try {
                        eventContainer.function.invoke(event);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    if(event.isCancelled()) cancelled=true;
                }
            }
        }

        return cancelled;
    }
    
}
