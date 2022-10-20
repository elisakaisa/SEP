package com.example.sep;

import com.example.sep.model.Employee;
import com.example.sep.model.Event;
import com.example.sep.model.FinancialRequest;
import com.example.sep.model.RecruitmentRequest;

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

    public static FinancialRequest fr1 = new FinancialRequest(
            4,
            "rfrf",
            "services",
            1000,
            "just give me a reason",
            FinancialRequest.APPROVED
    );

    public static FinancialRequest fr2 = new FinancialRequest(
            1,
            "jej",
            "services",
            2000,
            "just a little bit enough",
            FinancialRequest.PENDING
    );

    public static RecruitmentRequest rr1 = new RecruitmentRequest(
            "full time",
            "services",
            "minimum 3",
            "Responsible of whatever",
            "some HR bullshit",
            RecruitmentRequest.APPROVED,
            21
    );

    public static RecruitmentRequest rr2 = new RecruitmentRequest(
            "part time",
            "production",
            "minimum 75",
            "Master of nothing",
            "some corporate BS",
            RecruitmentRequest.PENDING,
            5
    );

    public static Employee employee1 = new Employee(
            "Name",
            "Department",
            "Role",
            "password"
    );
}
