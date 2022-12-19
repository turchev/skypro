#!/bin/bash
cd ../../skypro
mvn -pl arrays clean
mvn -pl arrays compile exec:exec
