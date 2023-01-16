#!/bin/bash
cd ../../skypro
mvn -pl exceptions clean
mvn -pl exceptions compile exec:exec
