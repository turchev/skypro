#!/bin/bash
cd ../../skypro
mvn -pl listqueue clean
mvn -pl listqueue compile exec:exec
