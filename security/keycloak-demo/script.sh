#!/bin/bash
# on remote container copy and execute the files from there:
# cd /opt/keycloak/bin/

./kcadm.sh config credentials --server http://localhost:8180 --realm master --user admin --password admin
./kcadm.sh create realms -s realm=spring-realm -s enabled=true -o
./kcadm.sh create users -r spring-realm -s username=demo -s enabled=true
./kcadm.sh set-password -r spring-realm --username demo --new-password demo
./kcadm.sh create clients -r spring-realm -s clientId=simple-webapp -s publicClient="true"  -s "redirectUris=[\"http://localhost:8080/*\"]" -s enabled=true
./kcadm.sh create roles -r spring-realm -s name=Users
./kcadm.sh add-roles --uusername demo --rolename Users -r spring-realm
