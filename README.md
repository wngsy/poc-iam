

Demo app for 


## Note
If using < keycloaker 13, ensure that you are running off  JDK/JRE 11 rather than down compiling/running ver 11 from a higher release version. If you are using spring source editor, you will need to change the build/compile path to the installed 11 version.


## Run Key Cloak via docker

Run keycloak using docker with h2 database and mounting the volume to retain persisted data.

docker run --volume keycloak:/opt/jboss/keycloak/standalone/data/ -p 8180:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:12.0.1


### Admin Configuration

Login to the admin at localhost:8180 using admin/admin

1. Create new Realm poc-iam
2. Create new Client poc-iam
	- ensure openid
	- redirect is set to the spring boot application url http://localhost:8080/*
3. Create role 'ROLE_USER'
4. Create a user and add role 'ROLE_USER'

