import exception.UnknownCommandException;
import exception.ChattyException;
import exception.DetailsUnknownException;

public class Duke {

    private Ui ui;
    private Messages messages;
    private TaskList tasklist;
    public Duke() {
        this.messages = new Messages();
        this.ui = new Ui();
        this.tasklist = new TaskList();
    }
    public static void main(String[] args) {
        new Duke().startChatting();
    }

    public void startChatting() {
        String exitC = "bye";
        String listC = "list";
        String deleteC = "delete";
        String doneC = "mark";
        String undoneC = "unmark";
        String deadlineC = "deadline";
        String eventC = "event";
        String todoC = "todo";

        String userInput;
        do {
            userInput = ui.getInput();
            Command command = new Command(tasklist, ui);

            if (!userInput.equalsIgnoreCase(exitC)) {
                try {
                    if (userInput.equalsIgnoreCase(listC)) {
                        command.handleList();
                    } else if (userInput.startsWith(deleteC)) {
                        command.handleDelete(userInput, deleteC);
                    } else if (userInput.startsWith(doneC)) {
                        command.handleDone(userInput, doneC);
                    } else if (userInput.startsWith(undoneC)) {
                        command.handleUndone(userInput, undoneC);
                    } else if (userInput.startsWith(deadlineC)) {
                        command.handleDeadline(userInput, deadlineC);
                    } else if (userInput.startsWith(eventC)) {
                        command.handleEvent(userInput, eventC);
                    } else if (userInput.startsWith(todoC)) {
                        command.handleTodo(userInput, todoC);
                    } else {
                        throw new UnknownCommandException();
                    }
                } catch (DetailsUnknownException e) {
                    System.out.println(e.getMessage());
                } catch (UnknownCommandException e) {
                    System.out.println(e.getMessage());
                }catch (ChattyException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!userInput.equalsIgnoreCase(exitC));

        this.messages.Exit();
    }
}
