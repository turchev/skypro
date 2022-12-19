#!/bin/bash
cd ../../skypro
mvn -pl methods clean
mvn -pl methods compile exec:exec
