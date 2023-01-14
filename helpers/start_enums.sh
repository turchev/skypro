#!/bin/bash
cd ../../skypro
mvn -pl enums clean
mvn -pl enums compile exec:exec
