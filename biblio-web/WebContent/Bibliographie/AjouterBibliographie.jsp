<%-- 
    Created on : Mars 28, 2016, 9:58:29 AM
    Author     : Mohamed Youssef ERRAYHANI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/biblio-web/resources/style.css" type="text/css" />
        <title>Livre</title>
    </head>
    <body>
         <section class="featured">
            <div class="content-wrapper">
                <hgroup class="title">
                    <h1>Bibliographie.</h1>
                    <h2>Ajoutez une biliographie </h2>
                </hgroup>
            </div>
        </section>
        <section class="content-wrapper main-content clear-fix">
            <h3> Details</h3>
            <form method="get" action="/biblio-web/AjouterBibliographie">
                <div>
                    <div>
                        <fieldset>
                            <label>Libellé</label>
                            <input type="text" id="Libelle" name="Libelle" /> 
                            <label>Source</label>
                            <input type="text" id="Source"name="Source"  /> 
                             <label>Source</label>
                            <select type="text" id="TypeIdentifiant"name="TypeIdentifiant"  > 
                             	<option value="1">Web</option>
                            </select>
                           
                            <br />
                        </fieldset>
                     </div>
                    <input type="submit" id="submit" title="Submit" value="Ajouter" />
                    <input type="reset" id="cancel" title="Cancel" value="Annuler" />

                </div>
            </form>
        </section>
        <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    </body>
</html>
