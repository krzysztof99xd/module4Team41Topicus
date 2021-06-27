Module4Team41

The project was done for the company Topicus which  came to the univeristy and asked about the web application which could help to analyze the risk of bank transactions.

The web app enables to update the MT940 file and perform the analysis based on the content of the file.

IMPORTANT : uploading a file only works with mt940 in the right format

link to trello board: 
https://trello.com/b/uTKpEGMY/group-41-topicus-bank-transaction-analysis-project

In order to use the file, the user first needs to log in. 
Afterwards, the user can make use of the application such as upoading the MT940 file, removing it from the table, making use of a search bar, performing the analysis of the account and logging out.

Technologies which were used in the project:
The back-end was written in Java, the following technologies were used:

In the project we made use of REST principles which are aligned with HTTP, making HTTP a suitable protocol to implement REST.

Jersey was used to implement web services standars in Java. Tomcat server was used to deploy a web application in an Application Server.
In our project we strongly make use of Java annotations in order to generate an additional code and files.

For the front-end:
We made use of Bootstrap in order to efficiently build our front-end.


List of developers:
Krzysztof Wiesniakowski
s2617722

Daniel Nicoara 
s2467593

Bart Aarts 
s2619776

Mohamed Mohamedin
s2241382


How to deploy the project:

In order to start the project, firstly the proper environment needs to be installed. In case of Eclipse it is going to be Eclipse IDE for Enterprise Java Developers and in case of Intelij it is going to be Intelij Ultimate.

To start the application, there needs to be the server to run the app. Tomcat server provides a good environment to run our project.

In order to do this in Eclipse, please follow the steps: 
Firstly, we have to create a server in Eclipse, so that we can assign our projects (web applications) to this server, and start and stop the server whenever we want. 
This can be done by se-lecting File → New → Other. . .→ Server → Server and selecting Apache→Tomcat v8.5 Server.
The wizard will ask for the Tomcat installation directory, choose the one where you saved your Tomcat directory.

For intelij Ultimate the steps are a little bit different: 
Go to run configuration
Select local tomcat
Then go down the window
There is a button named fix
Click it
Choose your war
And you can start the web app on tomcat server.



How to make use of our app:
First put the correct credentials into the log in page. 
Later you can explore our webapp. If you want to upload the file, click the button choose the file, choose MT940 file and click submit. 
Click analysis to perform the analysis of the specific accountID. Click "remove" in order to remove the accountID from the table. 
Click "Logout" in order to log out from the app.


