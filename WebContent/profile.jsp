<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
}

ul.header {
	color: lightblue;
}

li {
	float: left;
}

li a {
	display: block;
	color: grey;
	text-align: center;
	text-decoration: none;
	padding: 14px 16px;
}

li.search {
	padding: 2px 16px;
}

li a:hover {
	background-color: lightgreen;
}

.column {
	float: left;
	padding: 10px;
	box-sizing: border-box;
}

.column.side {
	width: 20%;
}

.column.middle {
	width: 80%;
}

.link {
	border: none;
	outline: none;
	background: none;
	cursor: pointer;
	display: block;
	color: blue;
	text-align: center;
	text-decoration: none;
	padding: 14px 16px;
}

.link:hover {
	background-color: lightgreen;
}

table {
	border: 1px solid black;
	width: 50%;
	background-color: white;
}

td, th {
	text-align: left;
}

th {
	width: 30%;
}

.fabutton {
	background: none;
	padding: 0px;
	border: none;
	cursor: pointer;
}

.top {
	background: none;
	padding: 0px;
	border: none;
	cursor: pointer;
	position: relative;
	top: 10px;
}

body {
	background-image:
		url("https://i.ytimg.com/vi/pegZ1w5W9N4/maxresdefault.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	image-rendering: -webkit-optimize-contrast;
	background-size: cover;
}
</style>
</head>
<body>
	<ul class="header">
		<li><form method="post" action="HomeCheck">
				<input type="submit" value="Home" class="link">
			</form></li>
		<li><form method="post" action="ProfileCheck">
				<input type="submit" value="Profile" class="link">
			</form></li>
		<li><form method="post" action="TweetCheck">
				<input type="submit" value="Tweet" class="link">
			</form></li>
		<li class="search"><form method="post" action="SearchCheck">
				<input type="text" name="search">
				<button type="submit" class="top">
					<i class="material-icons" style="color: blue">search</i>
				</button>
			</form></li>
		<li><form method="post" action="LogoutCheck">
				<input type="submit" value="Logout" class="link" style="color: red">
			</form></li>
		<li><form method="post" action="DeleteAccountCheck">
				<input type="hidden" name="delete" value="${uname}">
				<button type="submit" class="top">
					<i class="material-icons" style="color: red">block</i>
				</button>
			</form></li>
	</ul>

	<div class="column side">
		<h3 style="color: lightblue">${name}</h3>
		<p style="color: lightblue">@${uname}</p>
		<p style="color: lightblue">${about}</p>
		<p style="color: lightblue">${email}</p>
	</div>

	<div class="column middle">
		<ul class="header">
			<li><button class="link" onclick="myFunction()">Tweets</button></li>
			<li><button class="link" onclick="myFunction1()">Following</button></li>
			<li><button class="link" onclick="myFunction2()">Follower</button></li>
			<li><button class="link" onclick="myFunction3()">Likes</button></li>
		</ul>
	</div>

	<div class="column middle" id="hide" style="display: block">
		<c:forEach items="${message}" var="element" varStatus="loop">
			<table>
				<tr>
					<th>${name}</th>
					<td style="color: grey">@${uname}</td>
					<td><form method="post" action="DeletePostCheck">
							<input type="hidden" name="id" value="${id[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: red">clear</i>
							</button>
						</form></td>
				</tr>
				<tr>
					<td>${element}</td>
				</tr>
				<tr>
					<td><form method="post" action="CommentCheck">
							<input type="hidden" name="id" value="${id[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">sms</i>
							</button>
						</form>${numComment[loop.index]}
						<form method="post" action="DeleteCommentCheck">
							<select name="comment"><c:forEach
									items="${comment[loop.index]}" var="value" varStatus="turn">
									<option value="${commentid[loop.index][turn.index]}">${value}</option>
								</c:forEach></select>
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: red">clear</i>
							</button>
						</form></td>
					<td><form method="post" action="RetweetCheck">
							<input type="hidden" name="id" value="${id[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">sync</i>
							</button>
						</form>${numTweets[loop.index]}</td>
					<td><form method="post" action="LikeCheck">
							<input type="hidden" name="id" value="${id[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">favorite</i>
							</button>
						</form>${numLikes[loop.index]}</td>
				</tr>
			</table>
		</c:forEach>

		<c:forEach items="${fmessage}" var="element" varStatus="loop">
			<table>
				<tr>
					<th>${fname[loop.index]}</th>
					<td style="color: grey">@${funame[loop.index]}</td>
					<td><form method="post" action="FollowCheck">
							<input type="hidden" name="follow" value="${funame[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">how_to_reg</i>
							</button>
						</form></td>
				</tr>
				<tr>
					<td>${element}</td>
				</tr>
				<tr>
					<td><form method="post" action="CommentCheck">
							<input type="hidden" name="id" value="${fId[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">sms</i>
							</button>
						</form>${fnumComment[loop.index]}<form method="post"
							action="DeleteCommentCheck">
							<select name="comment">
								<c:forEach items="${fcomment[loop.index]}" var="value"
									varStatus="turn">
									<option value="${fcommentid[loop.index][turn.index]}">${value}</option>
								</c:forEach>
							</select>
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: red">clear</i>
							</button>
						</form></td>
					<td><form method="post" action="RetweetCheck">
							<input type="hidden" name="id" value="${fId[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">sync</i>
							</button>
						</form>${fnumTweets[loop.index]}</td>
					<td><form method="post" action="LikeCheck">
							<input type="hidden" name="id" value="${fId[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">favorite</i>
							</button>
						</form>${fnumLikes[loop.index]}</td>
				</tr>
			</table>
		</c:forEach>
	</div>

	<div class="column middle" id="hide1" style="display: none">
		<c:forEach items="${following}" var="element" varStatus="loop">
			<table>
				<tr>
					<th>${followingname[loop.index]}</th>
					<td style="color: grey">@${element}</td>
				</tr>
			</table>
		</c:forEach>
	</div>

	<div class="column middle" id="hide2" style="display: none">
		<c:forEach items="${follower}" var="element" varStatus="loop">
			<table>
				<tr>
					<th>${followername[loop.index]}</th>
					<td style="color: grey">@${element}</td>
				</tr>
			</table>
		</c:forEach>
	</div>

	<div class="column middle" id="hide3" style="display: none">

		<c:forEach items="${lmessage}" var="element" varStatus="loop">
			<table>
				<tr>
					<th>${lname[loop.index]}</th>
					<td style="color: grey">@${luname[loop.index]}</td>
					<td><form method="post" action="FollowCheck">
							<input type="hidden" name="follow" value="${luname[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">how_to_reg</i>
							</button>
						</form></td>
				</tr>
				<tr>
					<td>${element}</td>
				</tr>
				<tr>
					<td><form method="post" action="CommentCheck">
							<input type="hidden" name="id" value="${lId[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">sms</i>
							</button>
						</form>${lnumComment[loop.index]}
						<form method="post" action="DeleteCommentCheck">
							<select name="comment"><c:forEach
									items="${lcomment[loop.index]}" var="value" varStatus="turn">
									<option value="${lcommentid[loop.index][turn.index]}">${value}</option>
								</c:forEach></select>
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">clear</i>
							</button>
						</form></td>
					<td><form method="post" action="RetweetCheck">
							<input type="hidden" name="id" value="${lId[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">sync</i>
							</button>
						</form>${lnumTweets[loop.index]}</td>
					<td><form method="post" action="LikeCheck">
							<input type="hidden" name="id" value="${lId[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">favorite</i>
							</button>
						</form>${lnumLikes[loop.index]}</td>
				</tr>
			</table>
		</c:forEach>
	</div>

	<script>
		function myFunction() {
			var x = document.getElementById("hide");
			x.style.display = "block";
			var y = document.getElementById("hide1");
			y.style.display = "none";
			var z = document.getElementById("hide2");
			z.style.display = "none";
			var a = document.getElementById("hide3");
			a.style.display = "none";

		}
		function myFunction1() {
			var x = document.getElementById("hide");
			x.style.display = "none";
			var y = document.getElementById("hide1");
			y.style.display = "block";
			var z = document.getElementById("hide2");
			z.style.display = "none";
			var a = document.getElementById("hide3");
			a.style.display = "none";
		}
		function myFunction2() {
			var x = document.getElementById("hide");
			x.style.display = "none";
			var y = document.getElementById("hide1");
			y.style.display = "none";
			var z = document.getElementById("hide2");
			z.style.display = "block";
			var a = document.getElementById("hide3");
			a.style.display = "none";
		}
		function myFunction3() {
			var x = document.getElementById("hide");
			x.style.display = "none";
			var y = document.getElementById("hide1");
			y.style.display = "none";
			var z = document.getElementById("hide2");
			z.style.display = "none";
			var a = document.getElementById("hide3");
			a.style.display = "block";
		}
	</script>
</body>
</html>
