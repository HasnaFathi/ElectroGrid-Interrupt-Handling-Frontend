$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 $("#alertSuccess").hide();
	 }
	 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();
	// Form validation-------------------
	var status = validateInterruptForm();
	if (status != true)
	 {
	 $("#alertError").text(status);
	 $("#alertError").show();
	 return;
	 }
	// If valid------------------------
	var type = ($("#hidInterruptIDSave").val() == "") ? "POST" : "PUT";
	 $.ajax(
	 {
	 url : "InterruptsAPI",
	 type : type,
	 data : $("#formInterrupt").serialize(),
	 dataType : "text",
	 complete : function(response, status)
	 {
	 onInterruptSaveComplete(response.responseText, status);
 }
 });
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	 $("#hidInterruptIDSave").val($(this).data("interruptid"));
	 $("#interruptCode").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#date").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#Duration").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#Start_time").val($(this).closest("tr").find('td:eq(3)').text());
 	 $("#End_time").val($(this).closest("tr").find('td:eq(4)').text());
	 $("#Region").val($(this).closest("tr").find('td:eq(5)').text());
	 $("#Reason").val($(this).closest("tr").find('td:eq(6)').text());
	 $("#AdminID").val($(this).closest("tr").find('td:eq(7)').text());
	
	 
	 
});

$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
	 {
	 url : "InterruptsAPI",
	 type : "DELETE",
	 data : "InterruptID=" + $(this).data("interruptid"),
	 dataType : "text",
	 complete : function(response, status)
	 {
	 onInterruptDeleteComplete(response.responseText, status);
 }
 });
});


// CLIENT-MODEL================================================================
function validateInterruptForm()
{
	// CODE
	if ($("#interruptCode").val().trim() == "")
	 {
	 return "Insert Interrupt Code.";
	 }
	// DURATION-------------------------------
	if ($("#Duration").val().trim() == "")
	 {
	 return "Insert Duration.";
	 }
	// is numerical value
	var tmpPrice = $("#Duration").val().trim();
	if (!$.isNumeric(tmpPrice))
	 {
	 return "Insert a numerical value for Duration.";
 }
     // convert to decimal duration
	 $("#Duration").val(parseFloat(tmpPrice).toFixed(2));
	// START TIME------------------------
	if ($("#Start_time").val().trim() == "")
	 {
	 return "Insert Start Time.";
	 }
	// END TIME------------------------
	if ($("#End_time").val().trim() == "")
	 {
	 return "Insert End Time.";
	 }
	// REGION------------------------
	if ($("#Region").val().trim() == "")
	 {
	 return "Insert Region.";
	 }
	// REASON------------------------
	if ($("#Reason").val().trim() == "")
	 {
	 return "Insert Reason.";
	 }
	// ADMINID------------------------
	if ($("#AdminID").val().trim() == "")
	 {
	 return "Insert Admin ID.";
	 }
	
	
	return true;
}

function onInterruptDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divInterruptGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

function onInterruptSaveComplete(response, status)
{
	if (status == "success")
	 {
	 var resultSet = JSON.parse(response);
	 if (resultSet.status.trim() == "success")
	 {
	 $("#alertSuccess").text("Successfully saved.");
	 $("#alertSuccess").show();
	 $("#divInterruptGrid").html(resultSet.data);
	 } else if (resultSet.status.trim() == "error")
	 {
	 $("#alertError").text(resultSet.data);
	 $("#alertError").show();
	 }
	 } else if (status == "error")
	 {
	 $("#alertError").text("Error while saving.");
	 $("#alertError").show();
	 } else
	 {
	 $("#alertError").text("Unknown error while saving..");
	 $("#alertError").show();
 } 
	$("#hidInterruptIDSave").val("");
	 $("#formInterrupt")[0].reset();
}