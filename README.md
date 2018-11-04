################# DAILY TRADE REPORTING ENGINE #################

## How to run the application
Steps are as follows...

1. Clone the master branch of the project in your local
	```git clone -b master https://github.com/debabrata1708/trade_reporting_engine.git```

2. Perform maven build of the project

3. After successful maven build executable jar file will be generated in the following location
	```/trade_reporting_engine/target/trade_reporting_engine.jar```

4. Now from command line same can be executed in the following 2 ways...
	
	```java -jar target/trade_reporting_engine.jar```

Executing the jar in the above way will fetch test data from default file location which is as follows
/trade_reporting_engine/src/main/resources/trade.csv

	``` java -jar target/trade_reporting_engine.jar <path of the csv file>  ```

Executing the jar in the above way will fetch test data from specified path location. 

Please keep in mind that data format must be proper otherwise validation message will appear during execution.
So default csv file can be taken and modified with custom new data and placed in any location for testing purpose.

5. In either way with successful execution of jar file of step 4 result will be displayed in the console.
