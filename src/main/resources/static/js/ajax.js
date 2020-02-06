/**
 * Created by Keno&Kemo on 10.12.2017..
 */
var getJsonUsers = "/adminPage/json-users";
var getJsonTaxations = "/adminPage/json-taxations";
var getJsonArticles = "/adminPage/json-articles";
var token = $('#_csrf').attr('content');
var header = $('#_csrf_header').attr('content');

var userIdToDelete;
var rowIndexToDelete = "";
var codeRueToGet = "";
var codeArticleToGet = ""

$.ajaxSetup({
	headers : {
		'Content-Type' : 'application/json',
		'Accept' : 'application/json',
		'X-CSRF-TOKEN' : token
	}
});

function setRowIndexAndUserId(row, id) {
	userIdToDelete = id;
	rowIndexToDelete = row.parentNode.parentNode.rowIndex;
}
function setCodeRue(codeRue) {
	codeRueToGet = codeRue;
}
function setCodeArticle(codeArticle) {
	codeArticleToGet = codeArticle;
}

function closeModal(nameOfTheModal) {
	$(nameOfTheModal).modal('toggle');
}

function deleteEntity() {

	let deleteUserUrl = '/adminPage/json-users/delete/' + userIdToDelete;

	$
			.ajax({
				url : deleteUserUrl,
				type : 'DELETE',
				success : function() {

					let table = $("#user-table");
					table[0].deleteRow(rowIndexToDelete);

					$('#alert-messages')
							.append(
									"<div class='alert alert-success alert-dismissible fade show' role='alert'>"
											+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
											+ "<span aria-hidden='true'>&times;</span> </button>"
											+ "<strong>Well done!</strong> User has been deleted!!!"
											+ "</div>");
					closeModal('#deleteModal');
					userIdToDelete = "";
					rowIndexToDelete = "";
				}
			});
}
function deleteArticleEntity() {

	let deleteَArticleUrl = '/adminPage/json-articles/delete/' + userIdToDelete;

	$
			.ajax({
				url : deleteَArticleUrl,
				type : 'DELETE',
				success : function() {

					let table = $("#user-table");
					table[0].deleteRow(rowIndexToDelete);

					$('#alert-messages')
							.append(
									"<div class='alert alert-success alert-dismissible fade show' role='alert'>"
											+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
											+ "<span aria-hidden='true'>&times;</span> </button>"
											+ "<strong>Well done!</strong> Article has been deleted!!!"
											+ "</div>");
					closeModal('#deleteModal');
					userIdToDelete = "";
					rowIndexToDelete = "";
				}
			});
}

function searchUserByProperty() {
	let selectedProperty = $("#search-user-dropdown option:selected").text();
	let value = $("#searchUserBar").val();
	let entity = $("#searchEntityBar").val();

	if (value != null && value !== "") {

		switch (entity) {
		case "taxation":
			window.location.href = "/agentPage/taxations?taxationsProperty="
					+ selectedProperty + "&propertyValue=" + value;
		case "user":
			window.location.href = "/adminPage/users?usersProperty="
					+ selectedProperty + "&propertyValue=" + value;
		}
	}

	else {
		switch (entity) {
		case "taxation":
			window.location.href = "/adminPage/taxations";
		case "user":
			window.location.href = "/adminPage/users";
		}
	}

	// Old code:
	/*
	 * let getUsersByProperty = '/adminPage/json-users/search?usersProperty=' +
	 * selectedProperty + '&propertyValue=' + value;
	 * 
	 * $.ajax({ url: getUsersByProperty, type: 'GET', success: function (data,
	 * status, xhr) {
	 * 
	 * let tableBody = $("#user-table-body"); tableBody.empty(); $.each(data,
	 * function (i, e) { let end = e.id + ");'"; let del =
	 * "'setRowIndexAndUserId(this, " + end; let enabled; if (e.enabled ===
	 * true) { enabled = "<span style='color: green'>Enabled</span>" } else
	 * enabled = "<span style='color: red'>Disabled</span>";
	 * 
	 * let row = $('<tr>').append( $('<td>').text(e.id), $('<td>').text(e.name),
	 * $('<td>').text(e.surname), $('<td>').text(e.username), $('<td>').text(e.email),
	 * $('<td>').append(enabled), $('<td>').append( "<a
	 * style='text-decoration: none; color:blue' href='/adminPage/users/" + e.id +
	 * "'" + "class='editBtn' data-toggle='tooltip' data-placement='right'
	 * title='Edit user'>" + "<i class='fa fa-edit'></i></a>" ), $('<td>').append( "<a
	 * id='remove-link' style='text-decoration: none; color:red'" +
	 * "data-toggle='modal' data-placement='right' title='Remove user' " +
	 * "data-target='#deleteModal' onclick=" + del + "><i class='fa fa-times'
	 * aria-hidden='true'></i></a>" ) ); $('#user-table-body').append(row);
	 * }); }, error: function (jqXhr, textStatus, errorMessage) { let
	 * httpStatusCode = jqXhr.status;
	 * 
	 * if(httpStatusCode === 404){ $('#alert-messages').append( "<div
	 * class='alert alert-info alert-dismissible fade show' role='alert'>"+ "<button
	 * type='button' class='close' data-dismiss='alert' aria-label='Close'>"+ "<span
	 * aria-hidden='true'>&times;</span> </button>"+ "Sorry, no matches found
	 * for "+ selectedProperty + " = " + value + "</div>" ); } } });
	 */
}
function searchTaxationByProperty() {
	// let selectedProperty = $("#search-user-dropdown option:selected").text();
	let value = $("#newReclamationNum").val();

	if (value != null && value !== "") {

		return "/taxecommune/rest/v1/services/agentPage/json-taxations/"
				+ value;

	}

	else {
		// window.location.href = "/agentPage/taxations";
		return null;
	}
}
function getCelluleEntity() {

	setCodeRue($("#newCodeRue").val());
	let getCelluleUrl = '/adminPage/json-cellules/' + codeRueToGet;

	$.ajax({
		url : getCelluleUrl,
		type : 'GET',
		success : function(data, textStatus, xhr) {

			$("#newNumeroMunicipal").val(function(index, val) {
				// return val + $("#newCodeRue").val();
				var text = val.substr(0, 6);
				val = val.replace(text, data.code + $("#newCodeRue").val());
				return val;
			});
		},
		error : function(data, textStatus, xhr) {
			// alert(data.responseText);
			$("#newNumeroMunicipal").val(function(index, val) {
				// return val + $("#newCodeRue").val();
				var text = val.substring(0, 6);
				val = val.replace(text, "000000");
				return val;
			});
		}
	});
}
function getArticleEntity() {

	setCodeArticle($("#newNumeroArticle").val());
	let getArticleUrl = '/adminPage/json-articles/get/' + codeArticleToGet;

	$.ajax({
		url : getArticleUrl,
		type : 'GET',
		success : function(data, textStatus, xhr) {

			$("#newNumeroMunicipal").val(function(index, val) {
				// return val + $("#newCodeRue").val();
				var str = data.code
				var text = val.substr(6, 6);
				var text1 = str.substr(6, 6);
				val = val.replace(text, text1);
				return val;
			});
		},
		error : function(data, textStatus, xhr) {
			// alert(data.responseText);
			$("#newNumeroMunicipal").val(
					function(index, val) {
						// return val + $("#newCodeRue").val();
						var text = val.substr(6, 6);
						val = val.replace(text, codeArticleToGet
								+ $("#newCodeTCL").val());
						return val;
					});
		}
	});
}
function getTaxationEntity() {

	setCodeArticle($("#newNumeroArticle").val());
	let getTaxationeUrl = '/adminPage/json-taxations/get/' + codeArticleToGet;

	$.ajax({
		url : getTaxationUrl,
		type : 'GET',
		success : function(data, textStatus, xhr) {

			$("#newNumeroMunicipal").val(function(index, val) {
				// return val + $("#newCodeRue").val();
				var str = data.code
				var text = val.substr(6, 6);
				var text1 = str.substr(6, 6);
				val = val.replace(text, text1);
				return val;
			});
		},
		error : function(data, textStatus, xhr) {
			// alert(data.responseText);
			$("#newNumeroMunicipal").val(
					function(index, val) {
						// return val + $("#newCodeRue").val();
						var text = val.substr(6, 6);
						val = val.replace(text, codeArticleToGet
								+ $("#newCodeTCL").val());
						return val;
					});
		}
	});
}