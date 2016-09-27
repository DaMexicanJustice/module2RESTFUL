/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $("#qcontainer").html("READY");
   
   newRandomQuote();
   
   $("#qbutton").click(function() {
      newRandomQuote();
   });
   $("#qbuttonbyid").click(function() {
      newQuoteById();
   });
   $("#qbuttondelete").click(function() {
      deleteQuote();
   });
   $("#qbuttonadd").click(function() {
      addQuote();
   });
});

function newRandomQuote() {
    $.ajax({
      type : 'GET',
      url : 'api/quote/random'
   }).done(function (dataFromServer) {
       $("#qcontainer").html(dataFromServer);
   });
}

function newQuoteById() {
    $.ajax({
      type : 'GET',
      url : "api/quote/"+$("#qid").val()
   }).done(function (dataFromServer) {
       $("#qcontainer").html(dataFromServer);
   });
}

function deleteQuote() {
    $.ajax({
        type : 'DELETE',
        url : 'api/quote/'+$("#qid").val()
    }).done(function(dataFromServer) {
        $("#result").html(dataFromServer);
    });
}

function addQuote() {
    var query = JSON.stringify({quote: $("#qmsg").val()});
    $("qcontainer").html("");
    $.ajax({
        type : 'PUT',
        url : 'api/quote',
        data : query,
        contentType : 'text/plain'
    }).done(function(dataFromServer) {
        $("#result").html(dataFromServer);
    });
}