# weather-alerts
Sends alerts based on multiple different weather events.

# services used
Air Quality Alerts use webservices provided by AirNow: https://docs.airnowapi.org/webservices

# running the alert
```shell
$ java -jar target/alerts-1.0.0-SNAPSHOT.jar -Dsmtp.password=<your gmail password> -Dto=<email address you want the alert sent to>
```
