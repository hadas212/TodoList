# Todo List Java Project

A small Java project for managing a Todo List using standard Java only.

---

## Project Requirements

1. **Task Class**
    - Fields:
        - `id` (int) – unique identifier for the task.
        - `title` (String) – the task title.
        - `description` (String) – the task description.
        - `status` (Enum: NEW, IN_PROGRESS, DONE) – the task status.

2. **TaskRepository**
    - Manages the list of tasks and handles saving/loading from a JSON file.
    - Methods:
        - `add(Task task)` – add a new task.
        - `update(Task task)` – update an existing task.
        - `delete(int id)` – delete a task by ID.
        - `getById(int id)` – get a task by ID.
        - `listAll()` – return all tasks.
    - Uses a `List` to store tasks, with manual JSON save/load (`tasks.json`).

3. **TaskService**
    - Provides additional business logic on top of `TaskRepository`.
    - Methods:
        - `markAsDone(int id)` – mark a task as DONE.
        - `search(String keyword)` – search tasks by keyword in title or description.
        - `listSortedByStatus()` – return tasks sorted by status.

4. **Main Class**
    - Provides a simple console UI to demonstrate all functionalities:
        - Add a task.
        - Mark as DONE.
        - Delete a task.
        - Search tasks.
        - List all tasks.
        - List tasks sorted by status.
    - Uses `Scanner` for user input.

---

## How to Run

1. Make sure JDK is installed on your machine.
2. Save all files in the same directory:
    - `Task.java`
    - `Status.java`
    - `TaskRepository.java`
    - `TaskService.java`
    - `Main.java`
3. Compile and run the program from the command line or an IDE:
   ```bash
   javac *.java
   java Main
