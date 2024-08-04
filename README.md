# weather-alerts
Sends alerts based on multiple different weather events.

# services used
1. Air Quality Alerts use webservices provided by AirNow: https://docs.airnowapi.org/webservices
1. Temperature Apparent uses webservices provided by Tomorrow: https://docs.tomorrow.io/reference/get-timelines

# running the alert
The password can be found in your password manager.
```shell
$ java -Dsmtp.password="<your gmail app password>" -Dto=<comma separated lost of email address you want the alert sent to> -jar <path to jar>/alerts-1.0.0-SNAPSHOT.jar
```

# scheduling the alerter using the mac
1. Create an Automator Application to Run the Script
   Open Automator: You can find Automator in your Applications folder or by searching with Spotlight (Cmd + Space and type "Automator").
1. Create a New Document: Select "Application" when prompted to choose a type for your new document.
1. Add a "Run Shell Script" Action: In the Automator window, search for "Run Shell Script" in the Library on the left.
   Drag the "Run Shell Script" action to the workflow area on the right.
1. Enter Your Script:
   In the "Run Shell Script" action, enter the script you want to execute.
   Make sure your script is executable and includes the correct paths if necessary.
1. Save the Automator Application:
   Go to File > Save.
   Save the Automator application with a descriptive name, like "Run My Script," in a location you can easily find.

# schedule the script using calendar
1. Open the Calendar App:
   1. You can find it in your Applications folder or by searching with Spotlight.
1. Create a New Event:
   1. Click the + button to create a new event or double-click on the date/time you want the script to run.
1. Set the Event Time and Recurrence:
   1. Set the start time to when you want the script to execute.
   1. If you want the script to run regularly, you can set the event to repeat (e.g., daily, weekly, etc.).
1. Set the Alert to Run the Automator Application:
   1. In the event details, click on Alert.
   1. Choose Custom from the dropdown menu.
      1. In the alert options, choose Open file from the first dropdown menu.
      1. In the second dropdown, choose Other and locate the Automator application you created earlier. (Look in the ~/Documents folder)
1. Save the Event:
   1. Close the event details, and your event will now trigger the script at the scheduled time.
 