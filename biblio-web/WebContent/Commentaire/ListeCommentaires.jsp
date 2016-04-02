<%-- 
   
    Author     : Sara BAHJAJI
--%>

<%@page import="java.util.List"%>
<%@page import="fr.dauphine.lamsade.hib.biblio.modele.CommentaireDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! List<CommentaireDTO> commentaires =null; %>
<% commentaires = (List<CommentaireDTO> ) session.getAttribute("TableCommentaires"); %>
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
                    <h1>Commentaires.</h1>
                    <h2>Liste des commentaires. </h2>
                </hgroup>
            </div>
        </section>
        <section class="content-wrapper main-content clear-fix">
            <ol class="round">
            <li class="one">
                   <h3>Créer commentaire</h3>
                   <a href="/biblio-web/Commentaire/AjouterCommentaire.jsp" > Ajouter un nouveau commentaire</a>
            </li>
            <li class="two">
                <h3> Details</h3>
            <table>
                <thead>
                    <tr>
                        
                        <td><center>Commentaire</center></td>
                        <td><center>Date commentaire</center></td>
                        <td><center>Utilisateur</center></td>
                        <td><center>Libelle Bibliographie</center></td>
                     	<td><center>Source Bibliographie</center></td>
                    </tr>
                             
                </thead>
                <%for (int i = 0; i < commentaires.size(); i++) { %>
                <tr>
                    <td><center><%= commentaires.get(i).getCommentaire()%></center></td>
                    <td><center><%= commentaires.get(i).getDate_commentaire()%></center></td>
                    <td><center><%=  ((commentaires.get(i).getUtilisateur()!=null) ?commentaires.get(i).getUtilisateur().getMail():"") %></center></td>
                     <td><center><%=  ((commentaires.get(i).getBiblio()!=null) ?commentaires.get(i).getBiblio().getLibelle():"") %></center></td>
                      <td><center><%=  ((commentaires.get(i).getBiblio()!=null) ?commentaires.get(i).getBiblio().getSource():"") %></center></td>
                     <td><center><a href="ModifierCommentaire.jsp?Identifiant=<%= commentaires.get(i).getIdentifiant()+ "&Commentaire=" + commentaires.get(i).getCommentaire()  %> "  > Modifier </a>
                    <a href="/biblio-web/SupprimerCommentaire?Identifiant=<%= commentaires.get(i).getIdentifiant()%> "> Supprimer </a></center> </td>
               
                     </tr>
                
               <%  }%>
            </table>
         
            </li>
            </ol>
             
            
        </section>
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    </body>
</html>
