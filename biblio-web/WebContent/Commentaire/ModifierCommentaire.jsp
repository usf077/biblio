<%-- 
       Author     : Sara BAHJAJI
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/biblio-web/resources/style.css"
	type="text/css" />
<title>Livre</title>
</head>
<body>
	<section class="featured">
		<div class="content-wrapper">
			<hgroup class="title">
				<h1>Commentaire</h1>
				<h2>Modifier un commentaire</h2>
			</hgroup>
		</div>
	</section>
	<section class="content-wrapper main-content clear-fix">
		<h3>Details</h3>
		<form method="get" action="/biblio-web/ModifierCommentaire">
			<div>
				<div>
					<fieldset>
						<label>Commentaire</label> <input type="text" id="Commentaire"
							name="Commentaire"
							value="<%=request.getParameter("Commentaire")%>" /> <input
							type="hidden" id="Identifiant" name="Identifiant"
							value="<%=request.getParameter("Identifiant")%>" /> <br />
					</fieldset>
				</div>
				<input type="submit" id="submit" title="Submit" value="Modifier" />
				<input type="reset" id="cancel" title="Cancel" value=Annuler />

			</div>
		</form>
	</section>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
</body>
</html>
