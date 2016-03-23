Il s�agit d�une architecture compos�e principalement de 4 couches qui sont :
	1 et 2 : une couche pr�sentation et contr�leur repr�sent� par le sous projet biblio-web
	3 : une couche business repr�sent�e  par le sous projet EJB biblio-ejb
	4 : une couche interm�diaire repr�sent�e par le sous projet biblio-domaine et qui assure le faible couplage entre la partie pr�sentation/contr�leur et la partie business.
Le sous projet biblio-ear a pour objectif la creation d�un  � Entreprise archive � depuis le war de la couche pr�sentation et le jar de la couche business


Les Pr�requis 
	1-JDK 1.8
	2- Une base de donn�es Postgresql
	3- Le serveur D�application Glassfish 4.x
	4- L�IDE Eclipse Mars.2 Release (4.5.2)



Etape � suivre :
	1-	Cr�ez une base vide avec le nom � biblio � sous Postgresql
	2-	Restaurez la base � l�aide du fichier biblio.backup 
	3-	Ajouter le serveur d�application Glassfish � L�IDE  Eclipse
	4-	Build de l�application � l�aide de Maven install
	5-	D�ployez l�application  (biblio-ear) sous Glassfish 
