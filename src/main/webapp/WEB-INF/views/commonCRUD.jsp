<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="objectList" value="${classObject.classParameterList}" />
<c:set var="objectSize" value="1" />
<c:set var="listSize" value="0" />
<html>
<header> </header>
<body>
	<form name="tableForm" method="post" action="doPostForTable">
		<div id="tableAttributeDiv" width="80%" align="center">
			<fieldset>
				<legend>${tableName} Attributes</legend>
				<table width="80%" align="center">
					<c:forEach var="classVar1" items="${objectList}"
						varStatus="counter">
						<c:choose>
							<c:when test="${counter.count%2 != 0}">
								<tr>
									<td>${counter.count}</td>
									<td>${classVar1}</td>
									<td><c:choose>
											<c:when test="${classVar1 eq 'id'}">
												<input type="text" id="${classVar1}" name="${classVar1}"
													disabled />
											</c:when>
											<c:otherwise>
												<input type="text" id="${classVar1}" name="${classVar1}" />
											</c:otherwise>
										</c:choose></td>
							</c:when>
							<c:otherwise>
								<td>${counter.count}</td>
								<td>${classVar1}</td>
								<td><input type="text" id="${classVar1}"
									name="${classVar1}" /></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>

				<input type="button" name="clearAll" value="Clear All" id="clearAll"
					onclick="resetAll();clearData();clearRadio();" />&nbsp;&nbsp; <input
					type="button" name="save" id="save" value="Save" onclick="saveMe();" />&nbsp;&nbsp;
				<input type="button" name="update" id="update" value="Update"
					onclick="updateMe();" disabled />&nbsp;&nbsp; <input type="button"
					name="delete" id="delete" value="Delete" onclick="deleteMe();"
					disabled />
			</fieldset>
		</div>
	</form>
	<div id="tableDataList" width="80%" align="center">
		<fieldset>
			<legend>${tableName} Data List</legend>
			<table width="80%" align="center" border="2">
				<tr>
					<th>&nbsp;</th>
					<c:forEach var="classVar2" items="${objectList}">
						<c:set var="objectSize" value="${objectSize + 1 }" />
						<th>${classVar2}</th>
					</c:forEach>
				</tr>
				<c:choose>
					<c:when test="${objects != null && objects != ''}">
						<c:forEach var="object" items="${objects}" varStatus="myCounter">
							<c:set var="listSize" value="${listSize + 1 }" />
							<tr>
								<td><input type="radio" id="radio_${myCounter.count}"
									name="radioName" value="row_${myCounter.count}"
									onclick="onClickRadio('${object.id}')" /></td>
								<c:forEach var="classVar3" items="${objectList}">
									<td id="td_${classVar3}_${object.id}">${object[classVar3]}</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<td colspan="${objectSize}" align="center">No data Found</td>
					</c:otherwise>
				</c:choose>
			</table>
		</fieldset>
	</div>

</body>

<script type="text/javascript">
	function onClickRadio(id) {
		clearData();
		<c:forEach var="classVar4" items="${objectList}" varStatus="counter">
		document.getElementById('${classVar4}').value = document
				.getElementById('td_${classVar4}_' + id).textContent;
		</c:forEach>
		document.getElementById('update').disabled = false;
		document.getElementById('delete').disabled = false;
		document.getElementById('clearAll').disabled = false;
		document.getElementById('save').disabled = true;
	}

	function clearData() {
		<c:forEach var="classVar3" items="${objectList}" varStatus="counter">
		document.getElementById('${classVar3}').value = '';
		</c:forEach>

	}

	function clearRadio() {
		for ( var i = 1; i <= '${listSize}'; i++) {
			document.getElementById('radio_' + i).checked = false;
		}
	}
	
	function resetAll(){
		document.getElementById('update').disabled = true;
		document.getElementById('delete').disabled = true;
		document.getElementById('clearAll').disabled = false;
		document.getElementById('save').disabled = false;
	}
	
	function saveMe(){
		submitForm();
	}
	
	function updateMe(){
		submitForm();
	}
	
	function deleteMe(){
		submitForm();
	}
	
	function submitForm(){
		document.tableForm.submit();
		
	}
</script>
</html>
