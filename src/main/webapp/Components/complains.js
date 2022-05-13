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
var status = validateComplainForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid------------------------
var type = ($("#hidComplainIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "ComplainsAPI",
type : type,
data : $("#formComplain").serialize(),
dataType : "text",
complete : function(response, status)
{
onComplainSaveComplete(response.responseText, status);
}
});
});



function onComplainSaveComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();
$("#divComplainGrid").html(resultSet.data);
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

$("#hidComplainIDSave").val("");
$("#formComplain")[0].reset();
}



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidComplainIDSave").val($(this).closest("tr").find('#hidComplainIDUpdate').val());
 $("#cuscmID").val($(this).closest("tr").find('td:eq(0)').text());
 $("#accountNo").val($(this).closest("tr").find('td:eq(1)').text());
 $("#cDate").val($(this).closest("tr").find('td:eq(2)').text());
 $("#descri").val($(this).closest("tr").find('td:eq(3)').text());
});

// DELETE==========================================
$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
url : "ComplainsAPI",
type : "DELETE",
data : "complainID=" + $(this).data("complainid"),
dataType : "text",
complete : function(response, status)
{
onComplainDeleteComplete(response.responseText, status);
}
});
});



function onComplainDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divComplainGrid").html(resultSet.data);
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



// CLIENT-MODEL================================================================
function validateComplainForm()
{
// CODE
if ($("#cuscmID").val().trim() == "")
{
return "Insert Customer ID.";
}
// Name
if ($("#accountNo").val().trim() == "")
{
return "Insert Account Number.";
}

// PRICE-------------------------------
if ($("#cDate").val().trim() == "")
{
return "Insert Complain Date.";
}

// DESCRIPTION------------------------
if ($("#descri").val().trim() == "")
{
return "Insert Complain Description.";
}
return true;
}