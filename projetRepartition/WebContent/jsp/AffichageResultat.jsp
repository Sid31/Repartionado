<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>Répartition</title>
    <style type="text/css" >
    	body {background-color: rgb(158,253,56);}
		body > div {margin: 5%; border: 2px solid black; border-radius: 20px; text-align: center;}
		#rappelParam {background-color: #DBE414;}
    </style>
</head>
<body>
	<div id="rappelParam">
		<p>Salle : ${repartition.getSalle().getNom()} <br/>
		   Nombre de places assises : ${repartition.getSalle().size()} <br/>
		</p>
		<p>Promo : ${repartition.getPromotion().getNom()} <br/>
		   Nombre d'élèves : ${repartition.size()} <br/>
		</p>
	</div>
	<svg id="salle_svg" width="1400" height="650" style="margin: 0 5% 5% 5%; background-color: rgb(0,255,0); border: 2px solid black;">
		<c:forEach var="place" items="${repartition.getSalle()}">
			<rect width="80" height="60" x="${place.getX()}" y="${place.getY()}" fill="#FD5902" />
			<text x="${place.getX()+40}" y="${place.getY()+30}" font-size="8" style="text-anchor: middle;" > ${place.getNomPlace()} </text>
		</c:forEach>
		<c:forEach var="affectation" items="${repartition}">
			<rect id="${affectation.getPlace().getNomPlace()}" width="80" height="60" x="${affectation.getPlace().getX()}" y="${affectation.getPlace().getY()}" fill="#FD01CA" />
			<text x="${affectation.getPlace().getX()+40}" y="${affectation.getPlace().getY()+15}" font-size="8" style="text-anchor: middle;" > ${affectation.getEtudiant().getNom()} </text>
			<text x="${affectation.getPlace().getX()+40}" y="${affectation.getPlace().getY()+30}" font-size="8" style="text-anchor: middle;" > ${affectation.getEtudiant().getPrenom()} </text>
			<text x="${affectation.getPlace().getX()+40}" y="${affectation.getPlace().getY()+45}" font-size="8" style="text-anchor: middle;" > ${affectation.getPlace().getNomPlace()} </text>
		</c:forEach>
	</svg>
	<input type="button" id="boutonRetour" value="Retour" onclick="location.href='/projetRepartition/'" style="margin: 0 0 5% 48%;"/>
</body>
</html>