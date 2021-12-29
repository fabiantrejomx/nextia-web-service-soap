# Getting Started


# Steps in order to start the WS soap.

### Consideration
Use JAVA 8 to compile the application.

Install the dependencies using the following command
```sh
mvn clean install
```

### Database configuration
* By default, the application uses Postgres, if you want to use another database type, you must change the dialect and the datasource url.
in application.yml
* Change the username and password in application.yml if it is necessary.
* Create a DB by default the name is: taskdb
```sh
CRETE DATABASE taskdb
```

* Create the following tables. (One-to-many)
```sh
CREATE TABLE user_info(
  id VARCHAR(36) NOT NULL,
  email VARCHAR(36) NOT NULL,
  password VARCHAR(15) NOT NULL,
  PRIMARY KEY(id)
);
```

```sh
CREATE TABLE task(
  id VARCHAR(36) NOT NULL,
  subject VARCHAR(150) NOT NULL,
  done BOOLEAN NOT NULL,
  user_id VARCHAR(36) NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT user_info_task_fk FOREIGN KEY (user_id) REFERENCES user_info(id)
)
```

* Run the application as another java application by default the port is 8080

### How to see the contract
```sh
http://localhost:8080/ws/task.wsdl
```

### Test cases

Consider use SoapUI.

* Create
```sh
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:task="http://www.fabian.com/todo/soap/task">
    <soapenv:Header/>
    <soapenv:Body>
        <task:TaskRequest>
            <task:subject>?</task:subject>
            <task:isDone>?</task:isDone>
            <task:userId>?</task:userId>
        </task:TaskRequest>
    </soapenv:Body>
</soapenv:Envelope>
```

* Get All task by userId
```sh
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:task="http://www.fabian.com/todo/soap/task">
   <soapenv:Header/>
   <soapenv:Body>
      <task:AllTaskRequest>
         <task:userId>?</task:userId>
      </task:AllTaskRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

* Update
```sh
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:task="http://www.fabian.com/todo/soap/task">
   <soapenv:Header/>
   <soapenv:Body>
      <task:UpdateTaskRequest>
         <task:id>?</task:id>
         <task:subject>?</task:subject>
         <task:isDone>?</task:isDone>
      </task:UpdateTaskRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

* Delete
```sh
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:task="http://www.fabian.com/todo/soap/task">
   <soapenv:Header/>
   <soapenv:Body>
      <task:DeleteTaskRequest>
         <task:taskId>?</task:taskId>
      </task:DeleteTaskRequest>
   </soapenv:Body>
</soapenv:Envelope>
```



