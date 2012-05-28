<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<div id="header-wrapper">
	<div id="header">
		<div id="logo">
			<h1><a href="#">Priyank <span>Version 1</span></a></h1>
		</div>
	</div>
</div>
<div id="banner">
<form:form modelAttribute="userEntityBean" method="Post" action="Login">
<table>
	<tr>
		<td><img src="resources/images/img04.jpg" width="750" height="170" alt="" /></td>
		<td>
			<table>
				<c:if test="${loginFail == 'fail'}">
					<tr><td colspan="2"><font color="red"><b>Login failed, Please retry...</b></font></td></tr>
				</c:if>
				<tr><td colspan="2" class="date">Sign in</td></tr>
				<tr><td colspan="2" class="date">Email</td></tr>
				<tr><td colspan="2"><form:input path="email" /></td></tr>
				<tr><td colspan="2"><font color="red" size="1"><form:errors path="email" /></font></td></tr>
				<tr><td colspan="2" class="date">Password</td></tr>
				<tr><td colspan="2"><form:password path="userPassword" /></td></tr>
				<tr><td colspan="2"><font color="red" size="1"><form:errors path="userPassword" /></font></td></tr>
				<tr><td><input type="submit" value="SIGN IN" /></td><td class="date"><input type="checkbox"/> stay signed in</td></tr>
				<tr><td colspan="2"><a href="#"><font color="red">can't access your account</font></a></td></tr>
				<tr><td colspan="2"><a href="${pageContext.servletContext.contextPath}/RegisterMe">New User.. SignUp</a></td></tr>
			</table>
		</td>
	</tr>
</table>
</form:form>
</div>

<script type="text/javascript">
function signIn(){
	document.myForm.submit();
}
</script>
<script type="text/javascript" src="resources/js/jquery-1.7.2.js"></script>
</body>
</html>