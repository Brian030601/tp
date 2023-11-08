//@@ kherlenbayasgalan & jingxizhu

package seedu.duke.calendar;

import seedu.duke.calendar.command.EventCommand;
import seedu.duke.calendar.command.UnknownCommand;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CalendarManager {
    Calendar calendar;
    CalendarUi calendarUi;
    EventList eventList;
    CalendarCommandParser calendarCommandParser;
    Scanner scanner;

    private EventStorage storage;

    /**
     * The CalendarManager initializes the accesses to other classes.
     * It also loads events from the storage.
     * @param events is used to initialize the EventList.
     */

    public CalendarManager(ArrayList<Event> events) {

        EventDirectory eventdirectory = new EventDirectory();
        eventdirectory.listEventFiles();

        storage = new EventStorage(eventdirectory.defaultDirectory());

        try{
            eventList = storage.loadEvents();
        } catch (FileNotFoundException e){
            //System.out.println("Making new file for Events");
            eventList = new EventList(events);
        }

        calendar = new Calendar();
        calendarUi = new CalendarUi(eventList);
        calendarCommandParser = new CalendarCommandParser();
        scanner = new Scanner(System.in);

    }

    // getStorage is used for getting the storage
    public EventStorage getStorage(){
        return this.storage;
    }

    /**
     * validCommand is used for checking whether the command is valid, and
     * not an instance of UnknownCommand.
     * @param input is used for converting the input into command.
     * @return returns whether the command is instance of UnknownCommand or not.
     */

    public boolean validCommand(String input) {
        EventCommand command = calendarCommandParser.parseInput(input);

        return !(command instanceof UnknownCommand);
    }

    // isResponsible calls the validCommand method.
    public boolean isResponsible(String input) {
        return validCommand(input);
    }

    // processInput is used for saving the events in the EventList.
    public void processInput(String input) {
        startCalendar(input);

        storage.saveEvents(eventList.getEvent());
    }

    /**
     * startCalender starts the Calendar features and uses the input as a command.
     * @param input is used for converting the input into command.
     */

    public void startCalendar(String input) {
        EventCommand command = calendarCommandParser.parseInput(input);
        assert !(command instanceof seedu.duke.calendar.command.UnknownCommand) :
                "Command cannot be " + "unknown";
        calendarUi.executeCommand(command);
        //calendarCommandParser.parseInput(command);
    }
}
