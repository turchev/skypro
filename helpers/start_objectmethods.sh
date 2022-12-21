#!/bin/bash
cd ../../skypro
mvn -pl objectmethods clean
mvn -pl objectmethods compile exec:exec
