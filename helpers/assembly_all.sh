#!/bin/bash

cd ../../skypro

mvn clean
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

