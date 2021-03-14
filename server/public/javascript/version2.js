'use strict';

$('#contents').load('/login2')

function login() {
  const username = $('#loginName').val();
  const password = $('#loginPass').val();
  $('#contents').load('/validate2?username=' + username + '&password=' + password);
}

function createUser() {
  console.log('create user');
}
