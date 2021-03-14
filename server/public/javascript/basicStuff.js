'use strict';

console.log('Running js!');

$('#randomText').click(function() {
  $('#random').load('/random');
});
