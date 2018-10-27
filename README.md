### JavaEE EJB - Exchange
A simple project using EJB.

The example system is responsible for presents the exchange rate. The main requirements are:
- Should insert different type of currency;
- Should insert the exchange rate for a currency;
- Should show the temporal variation exchange.

#### Requirements
- JBoss AS 7.1;
- MySQL Server 5.7;
- MySQL Connector 5.1.24;
- Primefaces 6.2;
- JDK 1.7.0_80.

#### Configuration
It is necessary to configure the EJB container and MySQL database. 

##### Step 1 - Configure the MySQL module on JBoss
In the directory ".\jboss-as-7.1.1.Final\modules\com" add a new directory with the MySQL module. This directory needs three files: module.xml; mysql-connector-java-5.1.24-bin.jar; and mysql-connector-java-5.1.24-bin.jar.index. In the module.xml, it is necessary this configuration:
'''
<module xmlns="urn:jboss:module:1.0" name="com.mysql">
  <resources>
    <resource-root path="mysql-connector-java-5.1.24-bin.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
  </dependencies>
</module>
''' 

#### Step 2 - Configure file standalone.xml on JBoss
In the file standalone.xml on ".\jboss-as-7.1.1.Final\standalone\configuration" add this configurations:
'''
<datasource jndi-name="java:/exchangeDS" pool-name="exchangeDS" enabled="true" use-java-context="true">
    <connection-url>jdbc:mysql://localhost:3306/exchange</connection-url>
    <driver>com.mysql</driver>
    <pool>
        <min-pool-size>10</min-pool-size>
        <max-pool-size>100</max-pool-size>
        <prefill>true</prefill>
    </pool>
    <security>
        <user-name>root</user-name>
        <password>root</password>
    </security>
</datasource>
'''

'''
<driver name="com.mysql" module="com.mysql">
    <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
</driver>
'''

#### Step 3 - Prepare the Database
In MySQL, it is necessary to create the database. 
'''
create database exchange
'''




