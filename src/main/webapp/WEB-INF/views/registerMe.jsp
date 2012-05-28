<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp" />
<c:set var="secretQuestionMap" value="${secretQuestionMap}" />
<script type="text/javascript" src="resources/js/jquery-1.7.2.js"></script>
<style>
	.errors {
		background: #F7DEC0;
	}
</style>
<div id="banner">
	<form:form modelAttribute="userEntityBean" method="Post"
		action="RegisterMe">
		<div id="form_status">Incomplete</div>
		<table width="100%">
			<tr>
				<!-- 		<td><img src="resources/images/img04.jpg" width="750" height="170" alt="" /></td> -->
				<td>
					<fieldset>
						<legend>Register Me</legend>
						<table width="90%" align="center">
							<tr>
								<td class="date">Full Name</td>
								<td><form:input path="fullName" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="fullName" /></font></td>
							</tr>
							<tr>
								<td class="date">Nick Name</td>
								<td><form:input path="nickName" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="nickName" /></font></td>
							</tr>
							<tr>
								<td class="date">Email</td>
								<td><form:input path="email" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="email" /></font></td>
							</tr>
							<tr>
								<td class="date">Password</td>
								<td><form:password path="userPassword" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="userPassword" /></font></td>
							</tr>
							<tr>
								<td class="date">Re-Type Password</td>
								<td><form:password path="ReTypePassword" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="ReTypePassword" /></font></td>
							</tr>
							<tr>
								<td class="date">Secret Question</td>
								<td><form:select path="secretQuestionId" >
										<c:forEach items="${secretQuestionMap}" var="map">
											<form:option value="${map.key}">${map.value}</form:option>
										</c:forEach>
									</form:select></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="secretQuestionId" /></font></td>
							</tr>
							<tr>
								<td class="date">Secret Answer</td>
								<td><form:input path="secretAnswer" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="secretAnswer" /></font></td>
							</tr>
							<tr>
								<td><input type="submit" value="SIGN UP" /></td>
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
		</table>
	</form:form>
</div>
<script>
	$("#ReTypePassword").live("blur", function(){
		if(($("#userPassword").val() != $(this).val()) && ($("#userPassword").val().trim().length != 0) && ($(this).val().trim().length != 0)) {
			$("#userPassword").addClass("errors");
			$(this).addClass("errors");
		} else {
			$("#userPassword").removeClass("errors");
			$(this).removeClass("errors");
		}
	});
</script>