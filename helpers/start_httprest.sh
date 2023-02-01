#!/bin/bash
cd ../../skypro
mvn -pl httprest clean
mvn -pl httprest compile exec:exec
