# JSFApp

server.xml > * > Realm
durch folgendes Ersetzen:
```
<Realm className="org.apache.catalina.realm.JDBCRealm" 
      		driverName="com.mysql.jdbc.Driver"
      		connectionURL="jdbc:mysql://localhost:3306/tomcat_realm"
         	connectionName="root" connectionPassword=""
            userTable="tomcat_users" userNameCol="user_name" userCredCol="password"
      		userRoleTable="tomcat_users_roles" roleNameCol="role_name" />
```

# Benutzer

## Benutzer 1:
Username: user
Password: 123

## Benutzer 2 (Mit Admin Rechten):
Username: admin
Password: 123456


# Datenbank

Die Datenbank mit den Benutzer befinden sich in der tomcat_realm.sql File. Sie muss in der gleichnamigen Datenbank in mysql importiert werden.

Für Beispieldaten kann die Datenbank in der Datei jsfdb.sql importiert werden.

# Logging

Das Logfile wird unter "CATALINA_HOME/logs" abgespeichert.