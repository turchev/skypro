#!/bin/bash
cd ../../skypro
mvn -pl test clean
mvn -pl test compile exec:exec
