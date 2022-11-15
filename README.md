# My Personal Project - Sleep Pattern Tracker

## Project by Sharon Marfatia


**What my application will do:**
- My application will track a student's *sleep targets for the week in hours* and store data on their *sleep record
throughout the week in hours*. Using this information, the tracker will report their *sleep debt (hours of sleep 
targeted - actual hours of sleep)* or *sleep surplus*. Additional console-based questions will be asked from the user 
on whether they had *exams during the week or not* to compare how exams may or may not have affected the student's sleep 
time. This information can be shown through graphical representation (e.g., bar graph).

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
"Add your sleep entry". This button is on the menu page titled "Welcome to your SleepTracker!" You will be asked to
provide the day of the week you want to create an entry for, the number of hours you slept, and whether you have exams
or not (true/false). After filling out all three input prompts you will have created a new sleep entry (X) and it will 
get added to the list of sleep entries (Y). You can view the panel in which all the Xs that have already been added to Y
are displayed by clicking the button titled "View current entries". This button is on the menu page titled "Welcome to 
your SleepTracker!" Once you have viewed your list of entries you can return to the main menu by clicking the button 
titled "Return to Main Menu". To see a list of entries previously made, please reload the state of my application. You 
can also view entries on this panel after adding new entries as described above.
- You can generate the second required event related to adding Xs to a Y which is to remove a sleep entry by clicking on
the button labelled "Remove your sleep entry". This button is on the menu page titled "Welcome to your SleepTracker!"
You will be asked to provide the day of the week for which you had created an entry for earlier, which you now want to 
remove. After filling out that prompt, if an entry exists with that day, it will get removed from Y. 
(You can see this change on the json file "weeklySleep.json" if you click the button titled "Save entries file" on the 
main menu).
- You can locate my visual component by running Main in the ui package and an image of a fictional person sleeping will 
be displayed on the menu page titled "Welcome to your SleepTracker!"
- You can save the state of my application by clicking on the button titled "Save entries file". This button is on the 
menu page titled "Welcome to your SleepTracker!" Please load your file first before saving your entries. 
- You can reload the state of my application by clicking on the button titled "Load entries file". This button is on the
menu page titled "Welcome to your SleepTracker!" If you want to view the current entries saved on this file, then please
click the button titled "View current entries". This button is on the menu page titled "Welcome to your SleepTracker!"


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