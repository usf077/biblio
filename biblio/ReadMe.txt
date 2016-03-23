Il s’agit d’une architecture composée principalement de 4 couches qui sont :
	1 et 2 : une couche présentation et contrôleur représenté par le sous projet biblio-web
	3 : une couche business représentée  par le sous projet EJB biblio-ejb
	4 : une couche intermédiaire représentée par le sous projet biblio-domaine et qui assure le faible couplage entre la partie présentation/contrôleur et la partie business.
Le sous projet biblio-ear a pour objectif la creation d’un  « Entreprise archive » depuis le war de la couche présentation et le jar de la couche business


Les Prérequis 
	1-JDK 1.8
	2- Une base de données Postgresql
	3- Le serveur D’application Glassfish 4.x
	4- L’IDE Eclipse Mars.2 Release (4.5.2)



Etape à suivre :
	1-	Créez une base vide avec le nom « biblio » sous Postgresql
	2-	Restaurez la base à l’aide du fichier biblio.backup 
	3-	Ajouter le serveur d’application Glassfish à L’IDE  Eclipse
	4-	Build de l’application à l’aide de Maven install
	5-	Déployez l’application  (biblio-ear) sous Glassfish 
