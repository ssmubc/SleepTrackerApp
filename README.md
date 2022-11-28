# My Personal Project - Weekly SleepTracker for Students

## Project by Sharon Marfatia


**What my application will do:**
- My application will track a student's *sleep entries for the week in hours* and store data on their *sleep record
throughout the week in hours*. Additional console-based questions will be asked from the user on what *day of the week*
is being recorded, how many *hours they slept*, and whether they had *exams during the week or not* to compare how exams
may or may not have affected the student's sleep time. The application can also display an image through the GUI-based
program.

**Who will use it:**
- This application is intended for high school, undergraduate, or graduate students trying to improve their sleep 
patterns so that they can balance their studies and a healthy lifestyle. The Sleep Pattern Tracker will provide an 
honest representation of the amount of sleep you get in the week depending on the weekday and whether you are studying
for your exams on a time crunch.

**Why this project is of interest to me:**
- As a university student, I often suffer from a lack of sleep on an irregular pattern during exam periods.
Managing exam preparations while completing assignments and textbook readings requires more time commitment towards
studying, which can heavily reduce your sleep intake. Additionally, you often turn a blind eye to how much sleep you 
actually accumulated throughout the week. This application gives you a clear understanding of how your sleep pattern is 
looking so that you can direct yourself to change your habits for a healthier lifestyle. I see myself using an app like 
this, and so with this Sleep Pattern Tracker, I also want other students to benefit from a healthier approach to their 
daily sleep patterns.


# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by clicking on the button labelled 
"Add your sleep entry". This button is on the menu page titled "Weekly SleepTracker for Students" You will be asked to
provide the day of the week you want to create an entry for, the number of hours you slept, and whether you have exams
or not (true/false). After filling out all three input prompts you will have created a new sleep entry (X) and it will 
get added to the list of sleep entries (Y). You can view the panel in which all the Xs that have already been added to Y
are displayed by clicking the button titled "View current entries". This button is on the menu page titled 
"Weekly SleepTracker for Students" Once you have viewed your list of entries you can return to the main menu by clicking
the button titled "Return to Main Menu". To see a list of entries previously made, please reload the state of my 
application. You can also view entries on this panel after adding new entries as described above.
- You can generate the second required event related to adding Xs to a Y which is to remove a sleep entry by clicking on
the button labelled "Remove your sleep entry". This button is on the menu page titled "Weekly SleepTracker for Students"
You will be asked to provide the entry number you wish to remove. You can find this entry number by clicking on the 
button titled "View current entries" on the main menu. After selecting from the drop-down list and clicking "OK", it 
will get removed from Y. (You can see this change on the json file "weeklySleep.json" if you click the button titled
"Save entries file" on the main menu).
- You can locate my visual component by running Main in the ui package and an image of a fictional person sleeping will 
be displayed on the menu page titled "Weekly SleepTracker for Students".
- You can save the state of my application by clicking on the button titled "Save entries file". This button is on the 
menu page titled "Weekly SleepTracker for Students" Please load your file first before saving your entries. 
- You can reload the state of my application by clicking on the button titled "Load entries file". This button is on the
menu page titled "Weekly SleepTracker for Students." If you want to view the current entries saved on this file, then 
please click the button titled "View current entries." This button is on the menu page titled "Weekly SleepTracker for
Students."


*User Stories*

- As a user, I want to add the object (my day and night record of sleep - X) to my weekly sleep log class - Y so that I
get an array of objects (daily sleep pattern records) in the weekly sleep log class.


- As a user, I want to be able to view the list of daily sleep hours in my weekly sleep log class.


- As a user, I want to be able to edit a sleep log entry from my weekly sleep log.


- As a user, I want to be able to remove a sleep log entry from my weekly sleep log.

Phase 2 user stories:

- As a user, I want to save the entire state of my weekly sleep log to file. 

- As a user, I want to reload the state from file and continue where I had started on my weekly sleep log entry. 

CITATION: studied and referenced:
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java

CITATIONS for Phase 3 (read and studied) are included in the CreateGUI.java class

# Phase 4: Task 2
I have pasted a representative sample of the events that occur when my program runs. After starting the program I loaded
my existing entries from the file (weeklySleep.json). It contained 7 entries. I then added a new entry (8th entry) and 
saved it to the file. I then removed the 7th entry and saved the file. This resulted in a total of 7 entries being saved
to the file before the program was exited. The sample of events printed on the console is shown below. The event to add
a SleepModel to the SleepPerWeek class is called when the reader assigns reader.read() onto the instance of 
SleepPerWeek which is why it prints adding a new log after loading the existing data from the file. 

Entries loaded from file ./data/weeklySleep.json

Successfully added!!

The sleep entries is no longer existing

Sleep entries saved to file ./data/weeklySleep.json

Tue Nov 22 13:54:37 PST 2022

Added sleep entry to your log

Tue Nov 22 13:54:37 PST 2022

Added sleep entry to your log

Tue Nov 22 13:54:37 PST 2022

Added sleep entry to your log

Tue Nov 22 13:54:37 PST 2022

Added sleep entry to your log

Tue Nov 22 13:54:37 PST 2022

Added sleep entry to your log

Tue Nov 22 13:54:37 PST 2022

Added sleep entry to your log

Tue Nov 22 13:54:37 PST 2022

Added sleep entry to your log

Tue Nov 22 13:54:44 PST 2022

Added sleep entry to your log

Tue Nov 22 13:54:51 PST 2022

Removed a sleep entry from your log

# Phase 4: Task 3
Refactoring that I would do to improve my design:
- I could remove the association relationship between SleepModel and CreateGUI since there is already an association 
between SleepPerWeek and CreateGUI and SleepPerWeek contains SleepModel. This can be done by creating methods in the 
SleepPerWeek class that will get the fields of the SleepModel so that CreateGUI can access the fields of SleepModel
without having an association relationship with SleepModel.