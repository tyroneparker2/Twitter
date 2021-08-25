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

	<div class="column side"></div>

	<div class="column middle">
		<c:forEach items="${sMessage}" var="element" varStatus="loop">
			<table>
				<tr>
					<th>${sName[loop.index]}</th>
					<td style="color: grey">@${sUname[loop.index]}</td>
					<td><form method="post" action="FollowCheck">
							<input type="hidden" name="follow" value="${sUname[loop.index]}">
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
							<input type="hidden" name="id" value="${sId[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">sms</i>
							</button>
						</form>${sComment[loop.index]}
						<form method="post" action="DeleteCommentCheck">
							<select name="comment">
								<c:forEach items="${scomment[loop.index]}" var="value"
									varStatus="turn">
									<option value="${scommentid[loop.index][turn.index]}">${value}</option>
								</c:forEach>
							</select>
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: red">clear</i>
							</button>
						</form></td>
					<td><form method="post" action="RetweetCheck">
							<input type="hidden" name="id" value="${sId[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">sync</i>
							</button>
						</form>${sTweets[loop.index]}</td>
					<td><form method="post" action="LikeCheck">
							<input type="hidden" name="id" value="${sId[loop.index]}">
							<button type="submit" class="fabutton">
								<i class="material-icons" style="color: blue">favorite</i>
							</button>
						</form>${sLikes[loop.index]}</td>
				</tr>
			</table>
		</c:forEach>
	</div>
</body>
</html>