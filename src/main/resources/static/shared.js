$(document).ready(function() {


$('#restart').click(function(event) {
  // Stop the default action
  event.preventDefault();
  action = "POST";
  url = "/restart";

  $.ajax({
      type: action,
      url: url,
      dataType: "test",
      success: function(response, textStatus, jqXHR) {
          alert("restart happend!")
      }
  });
}); // End of Ajax


}); // End of document
