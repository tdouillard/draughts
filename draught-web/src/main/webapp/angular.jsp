
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/semantic.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css">
</head>
<body id="example" class="site" ng-app="draughtsAngularApp"
	ng-controller="MainCtrl">
	<script type="text/javascript">
		var apiEndpoint = '${pageContext.request.contextPath}/api/';
	</script>

	<div class="ui fixed inverted main menu">
		<div class="container">
			<div class="title item">
				<b>Draughts</b>
			</div>
		</div>
	</div>


	<div id="header" class="header segment">
		<div class="container">
			<h2 class="ui dividing header">Draughts</h2>
			<div class="introduction">
				<p>Simple Draughts app that makes use of JEE servlets</p>
			</div>

			<div ng-show="game.winner" id="winner"
				ng-class="{massive:true, circular:true, ui:true, black:game.winner=='BLACK', white:game.winner=='WHITE', icon:true,  button:true}">WINS</div>


		</div>
	</div>
	<script src="${pageContext.request.contextPath}/scripts/angular.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/main.js"></script>

</body>
</html>