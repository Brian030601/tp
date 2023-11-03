//@@author wendelinwemhoener

package seedu.duke.flashcard;

import seedu.duke.flashcard.command.FlashcardCommand;

import java.util.Scanner;

/**
 * Responsible for interfacing with the user by managing the dispatching of
 * commands to be executed and show to the user.
 */
public class FlashcardUi {
    private Scanner scanner;
    private FlashcardList flashcardList;

    /**
     * Instantiates and returns a new FlashcardUi.
     *
     * @param flashcardList The flashcards to be used for this Ui.
     */
    public FlashcardUi(FlashcardList flashcardList) {
        scanner = new Scanner(System.in);
        this.flashcardList = flashcardList;
    }

    /**
     * Executes a command and provides it with the appropriate context.
     * This context consists of a scanner for handling input and a
     * FlashcardList to execute actions on.
     *
     * @param command The command that shall be executed.
     */
    public void executeCommand(FlashcardCommand command) {
        command.execute(scanner, flashcardList);
    }

    /**
     * Set scanner method
     * for using bytearrayinputstream in test
     * @param scanner
     */
    public void setScanner(Scanner scanner){
        this.scanner = scanner;
    }
}
