#!/bin/bash

cd ../../skypro

mvn clean
mvn -pl coursework2 compile package
mvn -pl hashmap compile package
mvn -pl hashset compile package
mvn -pl listqueue compile package
mvn -pl exceptions compile package
mvn -pl enums compile package
mvn -pl oop compile package
mvn -pl coursework1 compile package
mvn -pl objectmethods compile package
mvn -pl objectsandclasses compile package
mvn -pl methods compile package
mvn -pl string compile package
mvn -pl arrays compile package
mvn -pl helloworld compile package
mvn -pl pro.sky:hw package

