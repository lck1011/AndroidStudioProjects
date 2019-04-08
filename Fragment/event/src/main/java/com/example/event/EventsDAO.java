package com.example.event;

import java.util.ArrayList;
import java.util.List;

public class EventsDAO {
    public static List<Events> list;

    static {
        list = new ArrayList<>();

        Events events = new Events();
        events.setName("Archery");
        events.setLogo("archery.png");
        events.setDecription("Archery dates back around 10,000years,...");
        list.add(events);

        events = new Events();
        events.setName("Athletics");
        events.setLogo("athletics.png");
        events.setDecription("Athletics is the perfect expression,...");
        list.add(events);

        events = new Events();
        events.setName("badminton");
        events.setLogo("badminton.png");
        events.setDecription("Athletics is the perfect expression,...");
        list.add(events);

        events = new Events();
        events.setName("basketball");
        events.setLogo("basketball.png");
        events.setDecription("Athletics is the perfect expression,...");
        list.add(events);

        events = new Events();
        events.setName("boxing");
        events.setLogo("boxing.png");
        events.setDecription("Athletics is the perfect expression,...");
        list.add(events);
    }
}
