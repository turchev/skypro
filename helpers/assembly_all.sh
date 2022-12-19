#!/bin/bash

cd ../../skypro

mvn clean
mvn -pl arrays compile package
mvn -pl helloworld compile package
mvn -pl pro.sky:hw package

