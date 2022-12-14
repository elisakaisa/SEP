# SEP

## Description

This mobile application for android (in java) was done in the scope of the final project for the course "Modern methods in software engineering" (ID 2207) at KTH in the fall term 2022.
The goal was top apply a subset of extreme programming approach (XP) practices:
1. Developing user stories
2. Release planning
3. Iteration planning
4. Selecting a system metaphor that could be suitable for the problem.
5. Developing a system in a test-driven fashion for selected user stories.
6. Refactoring the programs.
7. Pair programming.
8. Daily Stand-up meeting.

The purpose of this application was fulfill SEP's (a fictive event organising company) needs for a new system that allows the company to:
1. Initialise event requests: a customer service officer gets a call from a company wanting to organise an event. This requests gets redirected to the Senior customer service officer, then to the financial manager, then to the administration manager, and if the request is accepted, back to the senior customer service officer.
2. Distribute tasks to the sub-teams: the field manager (production or service) sends a lists of tasks to the sub-teams, which can then request additional budget or resources.
3. Staff recruitment management: the field sends the sub-teams request to HR, which decides whether to recruit or outsource.
4. Financial request management: the field managers request more budget from the financial department.

## Installation

The easiest way to run the project is installing Android studio. Once installed, open a new project and run the following command in the terminal:

```bash
git clone https://github.com/elisakaisa/SEP.git
```

To run the app, there are 2 options:
- To run the app in the emulator, follow the [following](https://developer.android.com/studio/run/managing-avds) steps from the android developer documentation.
- To run the app on your device, follow the [following](https://developer.android.com/studio/run/device) steps from the android developer documentation.

## Known issues

- none known

## Possible Improvements and extensions

- filtering the Events/Tasks/Requests or reordering them (requires to transform the delete function in the database classes to something similar than in TaskList)
- Adding a backend that deals with the logging in, and storing the events/tasks/requests
- budget checks: making sure that the budget assigned to different tasks is not bigger than the event budget
- displaying archived events in a separate tab
- Adding a way to add information to the event following the approval of the event and the physical meeting involving the client, the customer service and the financial manager
- Adding a way to update and archive the event once the event has been done
- Schedule and calendar with events and employer availabilities --> making sure an Employee cannot be scheduled on wo events/tasks at tzhe same time
- Improving the forms so that they take better or more relevant data
- Improving the UI
- more instrumented tests and UI tests

## Authors

Elisa Perini [github](https://github.com/elisakaisa) | [linkedIn](https://www.linkedin.com/in/elisa-perini-2759ba227/)

Panagiota Papadopoulou [github](https://github.com/katsikaktus)