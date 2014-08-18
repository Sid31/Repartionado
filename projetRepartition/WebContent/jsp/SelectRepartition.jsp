<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
  <head>
    <meta charset="utf-8" />
    <title>Sélection</title>
    <style type="text/css" >
    	body {background-color: rgb(158,253,56);}
		body > div {margin: 5%; border: 2px solid black; border-radius: 20px; text-align: center;}
		#titrePage {background-color: #DBE414;}
		#selection {padding: 5% 5% 3% 5%; background-color: rgb(0,255,0);}
		form div {position: relative; width: 30%; display: inline-block;}
		select {margin-left: 2%;}
    </style>
  </head>
  <body>
  
    <div id="titrePage" >
      <h1>Sélection de la répartition</h1>
    </div>
    
    <div id="selection" >
      <form method="get" action="repartition" >
        <div id="blockPromo">
	        <label for="promo" >Promotion : </label>
	        <select name="promo" id="promo" required>
	          <optgroup label="CPI">
	            <option value="cpi1">CPI 1</option>
	            <option value="cpi2">CPI 2</option>
	          </optgroup>
	          <optgroup label="ING">
	            <option value="ing1">ING1</option>
	            <option value="gsi">GSI</option>
	            <option value="sie">SIE</option>
	            <option value="icc">ICC</option>
	            <option value="erp">ERP</option>
	          </optgroup>
	        </select>
	    </div>
        <div id="blockSalle">
	        <label for="salle" >Salle : </label>
	        <select name="salle" id="salle" required>
	          <optgroup label="1er étage">
	            <option value="101">101</option>
	            <option value="102">102</option>
	            <option value="103">103</option>
	            <option value="104">104</option>
	            <option value="105">105</option>
	            <option value="106">106</option>
	          </optgroup>
	          <optgroup label="2ème étage">
	            <option value="201">201</option>
	            <option value="209">209</option>
	            <option value="210">210</option>
	            <option value="211">211</option>
	            <option value="212">212</option>
	            <option value="214">214</option>
	            <option value="215">215</option>
	            <option value="218">218</option>
	          </optgroup>
	        </select>
	    </div>
	    <br />
	    <br />
	    <br />
	    <input type="submit" value="Répartir" />
      </form>
    </div>
  </body>
</html>