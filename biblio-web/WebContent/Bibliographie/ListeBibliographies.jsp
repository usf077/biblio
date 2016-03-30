<%-- 
    Created on : Mars 28, 2016, 9:58:29 AM
    Author     : Mohamed Youssef ERRAYHANI
--%>

<%@page import="java.util.List"%>
<%@page import="fr.dauphine.lamsade.hib.biblio.modele.BibliographieDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! List<BibliographieDTO> bibliographies =null; %>
<% bibliographies = (List<BibliographieDTO> ) session.getAttribute("TableBibliographies"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/biblio-web/resources/style.css" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
         <section class="featured">
            <div class="content-wrapper">
                <hgroup class="title">
                    <h1>Bibliographies.</h1>
                    <h2>Liste des bibliographies. </h2>
                </hgroup>
            </div>
        </section>
        <section class="content-wrapper main-content clear-fix">
            <ol class="round">
            <li class="one">
                   <h3>Créer bibliographie</h3>
                   <a href="/biblio-web/Bibliographie/AjouterBibliographie.jsp" > Créer une nouvelle bibliographie</a>
            </li>
            <li class="two">
                <h3> Details</h3>
            <table>
                <thead>
                    <tr>
                        <td><center>Libellé</center></td>
                        <td><center>Source</center></td>
                        <td> <center> Type Bibliographie</center></td>
                    </tr>
                             
                </thead>
                <%for (int i = 0; i < bibliographies.size(); i++) { %>
                <tr>
                    <td><center><%= bibliographies.get(i).getLibelle() %></center></td>
                    <td><center><%= bibliographies.get(i).getSource()%></center></td>
                    <td><center><%=  ((bibliographies.get(i).getTypeBibliographie()!=null) ?bibliographies.get(i).getTypeBibliographie().getLibelle():"") %></center></td>
                     <td><center><a href="ModifierBibliographie.jsp?Identifiant=<%= bibliographies.get(i).getIdentifiant()+ "&Libelle=" + bibliographies.get(i).getLibelle() +"&Source=" + bibliographies.get(i).getSource() +"&TypeIdentifiant="+ ((bibliographies.get(i).getTypeBibliographie()!=null) ? bibliographies.get(i).getTypeBibliographie().getIdentifiant() :"" ) %> "  > Modifier </a>
                    <a href="/biblio-web/SupprimerBibliographie?Identifiant=<%= bibliographies.get(i).getIdentifiant()%> "  > Supprimer </a></center> </td>
               
                     </tr>
                
               <%  }%>
            </table>
         
            </li>
            </ol>
             
            
        </section>
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    </body>
</html>
