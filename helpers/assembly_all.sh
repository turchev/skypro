#!/bin/bash

cd ../../skypro

mvn clean
mvn -pl helloworld compile package
mvn -pl pro.sky:hw package

