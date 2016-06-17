<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Login</title>
		<link href="css/jquery.dataTables.min.css" type="text/css" rel="stylesheet">
		<script src="js/jquery.js"></script>
		<script src="js/dataTable.min.js"></script>
		<script src="js/jquery-ui.js"></script>
		<link rel="stylesheet" href="css/style.css" type="text/css">
		<link rel="stylesheet" href="css/jquery-ui.css" type="text/css">
		<link rel="stylesheet" href="css/dialog.css" type="text/css">
		<style>
		td, th{
			padding:10px;
		}
		button:hover{
			cursor:pointer;
		}
		
		.button {
			display: block;
			width: 85px;
			//height: 25px;
			background: #e6e6e6;
			padding: 10px;
			text-align: center;
			border-radius: 5px;
			color: red;
			font-weight: bold;
			float:left;
			margin-right: 10px;
			text-decoration:none;
		}
		a span{
			color:#555555;
		}
		
		td[id^=role]{
			text-transform: capitalize;
		}
		</style>
	</head>
	
<body>
<br>
<button id="create-user" class="ui-state-hover">Create new user</button>
<br>
<div id="users-contain" class="ui-widget" style="margin-top:20px;">
<table id="users" class="ui-widget ui-widget-content">
<thead>
	<tr class="ui-widget-header">
		<th class="dt[-head|-body]-left">S.No.</th>
		<th>User ID</th>
		<th>Username</th>
		<th>Password</th>
		<th>Role</th>
		<th>Operations</th>

	</tr>
</thead>
<tbody>
<c:forEach var="user" items="${userList}" varStatus="status">
	<tr>
		<td>${status.index + 1}</td>
		<td>${user.getId()}</td>
		<td id="username-${user.getId()}">${user.getUsername()}</td>
		<td id="password-${user.getId()}">${user.getPassword()}</td>
		<td id="role-${user.getId()}">${user.getRole()}</td>
		<td>
		<a href="#" onclick="deleteUser(${user.getId()})" class="button"><span>Delete user</span></a>
		<a href="#" onclick="openDialogUpdate(${user.getId()})" class="button"><span>Update user</span></a>
		<form method="post" action="delete.html" id="deleteUserForm-${user.getId()}">
			<input type="hidden" id="id" name="id" value='${user.getId()}'>
		</form>
		
		</td>
	</tr>	
</c:forEach>
</tbody>	
</table>
</div>
<script>
	$(document).ready(function(){
	$("#users").dataTable({
		  "columns": [
			{ "width": "10%" },
			{ "width": "10%" },
			{ "width": "10%" },
			{ "width": "20%" },
			{ "width": "10%" },
			{ "width": "40%" },
		  ]
	});
});

</script>

<script>
	var update_dialog;
  $(document).ready(function() {
    var dialog, form,
      emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
      username = $( "#username" ),
	  update_username = $( "#update_username" ),
     // email = $( "#email" ),
      password = $( "#password" ),
	  update_password = $("#update_password"),
      allFields = $( [] ).add( username ).add( password ),
	  updateallFields = $( [] ).add( update_username ).add( update_password )
      tips = $( ".validateTips" );
 
    function updateTips( t ) {
      tips
        .text( t )
        .addClass( "ui-state-highlight" );
      setTimeout(function() {
        tips.removeClass( "ui-state-highlight", 1500 );
      }, 500 );
    }
 
    function checkLength( o, n, min, max ) {
      if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( "Length of " + n + " must be between " +
          min + " and " + max + "." );
        return false;
      } else {
        return true;
      }
    }
 
    function checkRegexp( o, regexp, n ) {
      if ( !( regexp.test( o.val() ) ) ) {
        o.addClass( "ui-state-error" );
        updateTips( n );
        return false;
      } else {
        return true;
      }
    }
 
    function addUser() {
      var valid = true;
      allFields.removeClass( "ui-state-error" );
 
      valid = valid && checkLength( username, "username", 3, 16 );
      //valid = valid && checkLength( email, "email", 6, 80 );
      valid = valid && checkLength( password, "password", 5, 16 );
 
      valid = valid && checkRegexp( username, /^[a-z]([0-9a-z_\s])+$/i, "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
     // valid = valid && checkRegexp( email, emailRegex, "eg. ui@jquery.com" );
      valid = valid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
 
      if ( valid ) {
       $("#addUserForm").submit();
      }
      return valid;
    }
	
 
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 300,
      width: 350,
      modal: true,
      buttons: {
        "Create an account": addUser,
        Cancel: function() {
          dialog.dialog( "close" );
        }
      },
      close: function() {
      //  form[ 0 ].reset();
        allFields.removeClass( "ui-state-error" );
      }
    });
	
	update_dialog = $( "#dialog-form-update" ).dialog({
      autoOpen: false,
      height: 300,
      width: 350,
      modal: true,
      buttons: {
        "Change Password": updateUser,
        Cancel: function() {
          update_dialog.dialog( "close" );
        }
      },
      close: function() {
        //form[ 0 ].reset();
        updateallFields.removeClass( "ui-state-error" );
      }
    });
 
   /* form = dialog.find( "form" ).on( "submit", function( event ) {
      event.preventDefault();
      addUser();
    });*/
	
	$("#addUserBtn").click(function(){
		addUser();
	});
 
	$("#updateUserBtn").click(function(){
		updateUser();
	});
 
    $( "#create-user" ).button().on( "click", function() {
      dialog.dialog( "open" );
    });
	
	
	function updateUser(id){
		
		var valid = true;
		
		updateallFields.removeClass( "ui-state-error" );

		valid = valid && checkLength( update_password, "update_password", 5, 16 );

		valid = valid && checkRegexp( update_password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );

		if ( valid ) {
			$("#updateUserForm").submit();
		}
		return valid;
	}
	
	
	$("#updateUserBtn").click(function(){
		var id = $("#update_userid").val();
		updateUser(id);
	});
	
  });
	
  function openDialogUpdate(id){
		update_dialog.dialog( "open" );
		$("#update_userid").val(id);
		var username = $("#username-" + id).text();
		var password = $("#password-" + id).text();
		
		console.log(username + " " + password);
		
		$("#update_username").val(username);
		$("#update_password").val(password);
	}
	
	function deleteUser(id) {
		$( "#dialog-confirm" ).dialog({
		  resizable: false,
		  height:140,
		  modal: true,
		  buttons: {
			"Delete User": function() {
				$("#deleteUserForm-" + id).submit();
			  $( this ).dialog( "close" );
			},
			Cancel: function() {
			  $( this ).dialog( "close" );
			}
		}
    });
  }
  
  </script>
<div id="dialog-form" title="Create new user">
  <p class="validateTips">All form fields are required.</p>
 
  <form method="post" action="add.html" id="addUserForm" modelAttribute="user">
    <fieldset>
      <label for="name">UserName</label>
      <input type="text" name="username" id="username" class="text ui-widget-content ui-corner-all">
      <label for="password">Password</label>
      <input type="password" name="password" id="password" class="text ui-widget-content ui-corner-all">
	  <label for="password">Role</label>
      <select name="role" id="role" class="text ui-widget-content ui-corner-all">
		<option value="null">Select</option>
		<option value="admin">Admin</option>
		<option value="manager">Manager</option>
		<option value="ta">TA</option>
		<option value="screener">Screener</option>
		<option value="interviewer">Interviewer</option>
	  </select>
 
      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="button" tabindex="-1" style="position:absolute; top:-1000px" id="addUserBtn">
    </fieldset>
  </form>
</div>

<div id="dialog-form-update" title="Update user">
  <p class="validateTips">All form fields are required.</p>
 
  <form method="post" action="update.html" id="updateUserForm">
    <fieldset>
      <label for="name">UserName</label>
      <input type="text" name="update_username" id="update_username" class="text ui-widget-content ui-corner-all" disabled>
      <label for="password">New Password</label>
      <input type="password" name="update_password" id="update_password" class="text ui-widget-content ui-corner-all">
	  <input type="hidden" id="update_userid" name="update_userid" value="">
      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="button" tabindex="-1" style="position:absolute; top:-1000px" id="updateUserBtn">
    </fieldset>
  </form>
</div>

<div id="dialog-confirm" title="Empty the recycle bin?" style="display:none">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>These items will be permanently deleted and cannot be recovered. Are you sure?</p>
</div>


</body>
</html>

