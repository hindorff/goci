// Approve individual SNP associations

$(document).ready(function() {
    $("#approve-button").click(function() {
        // Passes study id to method
        approveSelectedAssociations($(this).attr("value"));
    });
});

function approveSelectedAssociations(studyId) {
    var associationIds = [];
    //create an array of association ids
    $('input.association-selector:checked').each(
            function() {
                associationIds.push($(this).attr("value"))
            }
    );
    updateAssociations(studyId, associationIds);
}

function updateAssociations(studyId, associationIds) {
    // Pass details to method in controller which handles database changes
    $.getJSON("/studies/" + studyId + "/associations/approve_checked",
              {"associationIds[]": associationIds},
            //Response
              function(data) {
                  alert(data.message);
                  //Reload page
                  location.reload();
              });
}

// Delele individual SNP associations
$(document).ready(function() {
    $("#delete-button").click(function() {
        // Passes study id to method
        deleteSelectedAssociations($(this).attr("value"));
    });
});

function deleteSelectedAssociations(studyId) {
    var associationIds = [];
    //create an array of association ids
    $('input.association-selector:checked').each(
            function() {
                associationIds.push($(this).attr("value"))
            }
    );
    deleteAssociations(studyId, associationIds);
}

function deleteAssociations(studyId, associationIds) {
    // Pass details to method in controller which handles database changes
    $.getJSON("/studies/" + studyId + "/associations/delete_checked",
              {"associationIds[]": associationIds},
            //Response
              function(data) {
                  alert(data.message);
                  //Reload page
                  location.reload();
              });
}
