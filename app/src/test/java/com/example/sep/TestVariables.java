package com.example.sep;

import com.example.sep.model.Event;

public class TestVariables {
    public static Event event1 = new Event(
            0,
            "utzftzuf",
            "client1",
            "party",
            "2022-03-12",
            "2022-03-13",
            "some comment",
            234,
            10000,
            true,
            true,
            true,
            false,
            false,
            Event.CS_CREATED,
            Event.PRELIMINARY

    );
    public static Event event2 = new Event(
            4,
            "hfg",
            "client2",
            "seminar",
            "2022-04-12",
            "2022-04-13",
            "other comment",
            342,
            50000,
            false,
            false,
            false,
            true,
            true,
            Event.CS_CREATED,
            Event.PRELIMINARY
    );

    public static Event event3 = new Event(
            2,
            "recordNumber",
            "clientName",
            "eventType",
            "fromDate",
            "toDate",
            "comments",
            10,
            1000,
            false,
            true,
            true,
            true,
            false,
            Event.CS_CREATED,
            Event.PRELIMINARY);
}
