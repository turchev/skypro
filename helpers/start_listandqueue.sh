#!/bin/bash
cd ../../skypro
mvn -pl listandqueue clean
mvn -pl listandqueue compile exec:exec
