$(document).ready(function() {
	checkPageSizes();
	changePageAndSize();
	searchUsersOnEnterKeyPressed();
	changeActiveLinks();
	keepSearchParametersAfterPageRefresh();
	showInputTextOnRadioButtonSelected();
	setNumeroMunicipalInitial();
	inputNumeroMunicipalOnFieldsChanged();
	inputNumeroMunicipalOnCodeArticlesChanged();
	showFieldsetOnChoixContribuableSelected();
	addNumRepWhenClicked();

});
function changeActiveLinks() {
	let qs = decodeURIComponent(location);
	let menuItem = qs.replace("http://localhost:8080", "");
	$("a[href='" + menuItem + "']").parent("li").addClass("active");

	if (menuItem.includes("adminPage")) {
		$("a[href='/adminPage']").parent("li").addClass("active");
	}
	if (menuItem.includes("users")) {
		$("a[href='/adminPage/users']").parent("li").addClass("active");
	}
	if (menuItem.includes("roles")) {
		$("a[href='/adminPage/roles']").parent("li").addClass("active");
	}
}

function changePageAndSize() {
	$('#pageSizeSelect').change(
			function(evt) {
				let selectedProperty = $(
						"#search-user-dropdown option:selected").text();
				let value = $("#searchUserBar").val();

				if (value != null && value !== "") {
					window.location.replace("/adminPage/users?usersProperty="
							+ selectedProperty + "&propertyValue=" + value
							+ "&pageSize=" + this.value + "&page=1");
				}

				else {
					window.location.replace("/adminPage/users?pageSize="
							+ this.value + "&page=1");
				}
			});
}

function searchUsersOnEnterKeyPressed() {
	$("#searchUserBar").keypress(function(event) {
		if (event.which === 13) {
			searchUserByProperty();
		}
	});
}

function searchTaxationOnEnterKeyUp() {
	$("#newReclamationNum").keyup(function(event) {
		if (event.which === 13) {
			searchTaxationByProperty();
		}
	});
}

function saveSearchParameters(e) {
	let id = e.id;
	let val = e.value;
	localStorage.setItem(id, val);// Every time user writing something, the
	// localStorage's value will override .
}

function keepSearchParametersAfterPageRefresh() {
	$("#searchUserBar").val(getSavedValueForTextBox("searchUserBar"));
	$("#search-user-dropdown").val(
			getSavedValueForDropDown("search-user-dropdown"));

	function getSavedValueForTextBox(v) {
		let usersPropertyParam = new URL(location.href).searchParams
				.get('usersProperty');
		if (localStorage.getItem(v) === null) {
			return "";
		} else if (usersPropertyParam === null) {
			return "";
		}

		return localStorage.getItem(v);
	}

	function getSavedValueForDropDown(v) {
		let propertyValue = new URL(location.href).searchParams
				.get('propertyValue');
		if (localStorage.getItem(v) === null) {
			return "ID";
		} else if (propertyValue === null) {
			return "ID";
		}

		return localStorage.getItem(v);
	}
}

// If the page size option is greater than total number of elements (users) -
// disable it
function checkPageSizes() {
	let pageSizesToShow = $('#pageSizesToShow').data('pagesizestoshow');

	$("#pageSizeSelect option").each(function(i, option) {
		if ($.inArray(parseInt(option.value), pageSizesToShow) === -1) {
			option.disabled = true;
		}
	});
}

function sortTable(n) {
	let table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	table = document.getElementById("user-table");
	switching = true;
	// Set the sorting direction to ascending:
	dir = "asc";
	/*
	 * Make a loop that will continue until no switching has been done:
	 */
	while (switching) {
		// start by saying: no switching is done:
		switching = false;
		rows = table.getElementsByTagName("TR");
		/*
		 * Loop through all table rows (except the first, which contains table
		 * headers):
		 */
		for (i = 1; i < (rows.length - 1); i++) {
			// start by saying there should be no switching:
			shouldSwitch = false;
			/*
			 * Get the two elements you want to compare, one from current row
			 * and one from the next:
			 */
			x = rows[i].getElementsByTagName("TD")[n];
			y = rows[i + 1].getElementsByTagName("TD")[n];
			/*
			 * check if the two rows should switch place, based on the
			 * direction, asc or desc:
			 */

			if (dir == "asc") {
				// if user clicks on id column, compare numbers
				if (n === 0) {
					// compare numbers
					if (Number(x.innerHTML) > Number(y.innerHTML)) {
						// if so, mark as a switch and break the loop:
						shouldSwitch = true;
						break;
					}
				} else if (x.innerHTML.toLowerCase() > y.innerHTML
						.toLowerCase()) {
					// if so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			} else if (dir == "desc") {
				// if user clicks on id column, compare numbers
				if (n === 0) {
					// compare numbers
					if (Number(x.innerHTML) < Number(y.innerHTML)) {
						// if so, mark as a switch and break the loop:
						shouldSwitch = true;
						break;
					}
				} else if (x.innerHTML.toLowerCase() < y.innerHTML
						.toLowerCase()) {
					// if so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			}
		}
		if (shouldSwitch === true) {
			/*
			 * If a switch has been marked, make the switch and mark that a
			 * switch has been done:
			 */
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			// Each time a switch is done, increase this count by 1:
			switchcount++;
		} else {
			/*
			 * If no switching has been done AND the direction is "asc", set the
			 * direction to "desc" and run the while loop again.
			 */
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}
/*
 * Form
 */
function showInputTextOnRadioButtonSelected() {

	$('#cin').hide();
	$('#codFis').hide();
	$('#numPasseport').hide();
	$('#numSejour').hide();

	$('input[name="idChoice"]').click(function(e) {
		switch (e.target.value) {
		case 'cin':
			$('#cin').show();
			$('#codFis').hide();
			$('#numPasseport').hide();
			$('#numSejour').hide();
			break;
		case 'codFis':
			$('#cin').hide();
			$('#codFis').show();
			$('#numPasseport').hide();
			$('#numSejour').hide();
			break;
		case 'numPasseport':
			$('#cin').hide();
			$('#codFis').hide();
			$('#numPasseport').show();
			$('#numSejour').hide();
			break;
		case 'numSejour':
			$('#cin').hide();
			$('#codFis').hide();
			$('#numPasseport').hide();
			$('#numSejour').show();
			break;
		}
	})
}
function setNumeroMunicipalInitial() {
	$("#newNumeroMunicipal").val(function(index, val) {
		val = "000000000" + $("#newCodeTCL").val();
		return val;
	});
}

function inputNumeroMunicipalOnFieldsChanged() {
	$("#newCodeRue").keyup(function(evt) {
		$.getScript('/js/ajax.js', function(e) {
			// Call custom function defined in script
			getCelluleEntity();
		});
	});
}
function inputNumeroMunicipalOnCodeArticlesChanged() {
	$("#newNumeroArticle").keyup(function(evt) {
		$.getScript('/js/ajax.js', function(e) {
			// Call custom function defined in script
			getArticleEntity();
		});
	});
}
function showFieldsetOnChoixContribuableSelected() {

	$('#fieldProp').hide();
	$('#fieldNonProp').hide();

	$('input[name="choixCont"]').click(function(e) {
		switch (e.target.value) {
		case 'proprietaire':
			$('#fieldProp').show();
			$('#fieldNonProp').hide();
			break;
		case 'locataire':
			$('#fieldProp').hide();
			$('#fieldNonProp').show();
			break;
		case 'occupant':
			$('#fieldProp').hide();
			$('#fieldNonProp').show();
			break;
		}
	})
}
function addNumRepWhenClicked() {

	$('#newNumRep').change(function() {
		if (this.checked) {
			$("#newNumeroMunicipal").val(function(index, val) {
				var str = val.substr(10, 2);				
				var myInteger = parseInt(str);
				++myInteger;							
				val = val.substr(0, 10) + myInteger;
				return val;
			});
		} else {
			$("#newNumeroMunicipal").val(function(index, val) {
				var str = val.substr(10, 2);
				var myInteger = parseInt(str);
				--myInteger;
				val = val.substr(0, 10) + myInteger;
				return val;
			});
		}
	});
}