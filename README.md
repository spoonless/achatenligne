Appli de démo Java EE
=====================

L'application doit pouvoir se connecter à une base de données.
Le schéma de la base est dans le fichier src/main/sql/database.sql

Il faut configurer Tomcat pour déclarer une datasource du nom de bdd/produits.
Pour cela, il faut ajouter le driver de base de données dans $TOMCAT_HOME/lib
et dans le fichier server.xml, il faut déclarer la datasource en ajoutant
la balise Resource dans la balise Context décrivant le déploiement de l'application :

```xml
  <Context docBase="achatenligne" 
           path="/achatenligne" 
           reloadable="true" 
           source="org.eclipse.jst.jee.server:achatenligne">

	  <Resource name="bdd/produits"
	            auth="Container"
	            type="javax.sql.DataSource"
	            maxTotal="100"
	            maxIdle="30"
	            maxWaitMillis="10000"
	            username="root"
	            password="root"
	            driverClassName="com.mysql.jdbc.Driver"
	            url="jdbc:mysql://localhost:3306/produit" />

  </Context>
```