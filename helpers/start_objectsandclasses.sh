#!/bin/bash
cd ../../skypro
mvn -pl objectsandclasses clean
mvn -pl objectsandclasses compile exec:exec
