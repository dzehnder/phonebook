# phonebook
School Project (Java Security Module)


## JSFApp

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