# weather-alerts
Sends alerts based on multiple different weather events.

# services used
1. Air Quality Alerts use webservices provided by AirNow: https://docs.airnowapi.org/webservices
1. Temperature Apparent uses webservices provided by Tomorrow: https://docs.tomorrow.io/reference/get-timelines

# running the alert
```shell
$ java -Dsmtp.password="<your gmail app password>" -Dto=<comma separated lost of email address you want the alert sent to> -jar <path to jar>/alerts-1.0.0-SNAPSHOT.jar
```
