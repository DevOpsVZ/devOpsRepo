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
		
		ul#navbar {
			list-style-type: none;
			margin: 0;
			padding: 0;
			overflow: hidden;
			background-color: #333;

		}

		#navbar li {
			float: left;
		}

		#navbar li a {
			display: block;
			color: white;
			text-align: center;
			padding: 14px 16px;
			text-decoration: none;
		}

		#navbar li a:hover {
			background-color: #111;
		}
		</style>
	</head>
	
<body>
<br>

<ul id="navbar">
		  <li><a href="manager.html">Home</a></li>
		  <li><a class="active" href="jobs.html">Jobs</a></li>
</ul>
<br>

<button id="create-user" class="ui-state-hover">Create new Job</button>
<br>
<div id="users-contain" class="ui-widget" style="margin-top:20px;">
<table id="users" class="ui-widget ui-widget-content">
<thead>
	<tr class="ui-widget-header">
		<th class="dt[-head|-body]-left">S.No.</th>
		<th>Job ID</th>
		<th>Job title</th>
		<th>Description</th>
		<th>Assigned To</th>
		<th>Status</th>
		<th>Operations</th>

	</tr>
</thead>
<tbody>
<c:forEach var="job" items="${jobsList}" varStatus="status">
	<tr>
		<td>${status.index + 1}</td>
		<td>${job.getId()}</td>
		<td id="jobTitle-${job.getId()}">${job.getJobTitle()}</td>
		<td id="description-${job.getId()}">${job.getDescription()}</td>
		<td id="assignedTo-${job.getId()}">${job.getAssignedTo()}</td>
		<td id="status-${job.getId()}">${job.getStatus()}</td>
		<td>
		<a href="#" onclick="deleteJob(${job.getId()})" class="button"><span>Delete Job</span></a>
		<a href="#" onclick="openDialogUpdate(${job.getId()})" class="button"><span>Update Job</span></a>
		<form method="post" action="deleteJob.html" id="deleteJobForm-${job.getId()}">
			<input type="hidden" id="id" name="id" value='${job.getId()}'>
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
			{ "width": "10%" },
			{ "width": "20%" },
			{ "width": "10%" },
			{ "width": "30%" },
		  ]
	});
});

</script>

<script>
	var update_dialog;
  $(document).ready(function() {
    var dialog, form,
      emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
      jobTitle = $( "#jobTitle" ),
	  update_username = $( "#update_username" ),
     // email = $( "#email" ),
      description = $( "#description" ),
	  update_password = $("#update_password"),
      allFields = $( [] ).add( jobTitle ).add( description ),
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
 
    function createJob() {
      var valid = true;
      allFields.removeClass( "ui-state-error" );
 
      valid = valid && checkLength( jobTitle, "Job Title", 3, 16 );
      //valid = valid && checkLength( email, "email", 6, 80 );
      valid = valid && checkLength( description, "description", 5, 160 );
 
      valid = valid && checkRegexp( jobTitle, /^[a-z]([0-9a-z_\s])+$/i, "Job title may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
     // valid = valid && checkRegexp( email, emailRegex, "eg. ui@jquery.com" );
      valid = valid && checkRegexp( description, /^[a-z]([0-9a-z_\s])+$/i, "Description may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
 
      if ( valid ) {
       $("#createJobForm").submit();
      }
      return valid;
    }
	
 
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 300,
      width: 350,
      modal: true,
      buttons: {
        "Create a Job": createJob,
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
        "Change Password": createJob,
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
	
	$("#createJobBtn").click(function(){
		createJob();
	});
 
	$("#updateUserBtn").click(function(){
		updateUser();
	});
 
    $( "#create-user" ).button().on( "click", function() {
      dialog.dialog( "open" );
    });
	
	
	
	
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
	
	function deleteJob(id) {
		$( "#dialog-confirm" ).dialog({
		  resizable: false,
		  height:140,
		  modal: true,
		  buttons: {
			"Delete User": function() {
				$("#deleteJobForm-" + id).submit();
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
 
  <form method="post" action="createJob.html" id="createJobForm" modelAttribute="job">
    <fieldset>
      <label for="name">Job Title</label>
      <input type="text" name="jobTitle" id="jobTitle" class="text ui-widget-content ui-corner-all">
      <label for="description">Description</label>
	  <textarea name="description" id="description" class="text ui-widget-content ui-corner-all" cols=41></textarea>
		
	 <label for="Assign To">Assign To</label>
	 <select name="assignedTo" id="assignedTo" class="text ui-widget-content ui-corner-all">
	 <c:forEach var="ta" items="${taList}" varStatus="status">
		<option value="${ta.getId()}">${ta.getUsername()}</option>
	 </c:forEach>
	 </select>
      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="button" tabindex="-1" style="position:absolute; top:-1000px" id="createJobBtn">
    </fieldset>
  </form>
</div>

<div id="dialog-form-update" title="Update user">
  <p class="validateTips">All form fields are required.</p>
 
  <form method="post" action="updateUser.html" id="updateUserForm">
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

