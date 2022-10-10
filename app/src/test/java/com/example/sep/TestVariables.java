package com.example.sep;

import com.example.sep.model.Event;

public class TestVariables {
    public static Event event1 = new Event(
            "id",
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
            0
    );
    public static Event event2 = new Event(
            "id2",
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
            0
    );

    public static Event event3 = new Event(
            "id",
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
            0);
}
