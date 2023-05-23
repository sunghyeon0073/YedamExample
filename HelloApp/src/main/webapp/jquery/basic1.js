// basic2.js

$(document).ready(function(){ // document -> jQuery
  console.log($('ul>li:nth-of-type(1)'));

  $('ul>li:nth-of-type(1)').css('background', 'yellow');
  $('ul>li:nth-of-type(2)').css('color', 'red');

  $('#show button:eq(0)').css('background', 'yellow');
  $('span:eq(1)').html('<b>월드</b>');

  console.log($('div span:nth-of-type(1)'));
  $('div>p:nth-of-type(1)>span').css('color', 'red');

  $('div#show>p:gt(0)>span').css('background', 'red');

  $('#show2>p:not(:eq(1))>span').css('background', 'yellow');

  $('span:contains(Good)').css('fontSize', '20px');

  $('p:has(b)').css('background', 'green');
});