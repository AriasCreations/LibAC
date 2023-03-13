package dev.zontreck.ariaslib.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class EventsListenerList {

    public Map<Class<?>, List<EventContainer>> events = Maps.newHashMap();

    public void addEventMethod(Class<?> e, Method action, Class<?> clazz)
    {

        // Add a new container!
        EventContainer contains = new EventContainer();
        contains.function=action;
        contains.containingClass = clazz;
        
        if(!events.containsKey(e)){
            List<EventContainer> tmp = Lists.newArrayList();
            tmp.add(contains);
            events.put(e, tmp);
        }else {
            events.get(e).add(contains);
        }
    }
    
}
