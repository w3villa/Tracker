<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.w3villa.constants.TrackerConstant" %>
<html>
<link href="resources/css/style.css" rel="stylesheet" type="text/css"
	media="screen" />
<c:set var="userEntityBean" value="${sessionScope.userEntityBean}" />
<body>
	<table width="100%">
		<tr>
			<td style="text-align: right"><font color="blue" size="2"><i>
						<c:if test="${userEntityBean != null}">Welcome ${userEntityBean.email}</c:if>
						<c:if test="${userEntityBean == null}">Welcome Guest</c:if>
				</i> </font></td>
		</tr>
	</table>
	</li>
	<div id="menu-wrapper">
		<div id="menu">
			<ul>
				<li class="current_page_item"><a
					href="${pageContext.servletContext.contextPath}/">Homepage</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/Preeti">About</a></li>
				<li><a href="#">Contact</a></li>
				<c:if test="${userEntityBean != null}">
					<li><a
						href="${pageContext.servletContext.contextPath}/crud?tableName=<%=TrackerConstant.SECRET_QUESTION_BEAN%>">secret
							Question</a></li>
					<li><a
						href="${pageContext.servletContext.contextPath}/crud?tableName=<%=TrackerConstant.USER_ENTITY_BEAN%>">User
							Management</a></li>
				</c:if>
			</ul>
		</div>
		<!-- end #menu -->
	</div>
</body>
</html>