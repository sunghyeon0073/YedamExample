// basic.js
 // document.addEventListener('DOMContentLoaded', function(){
  //   let liTag = document.createElement('li');  // Document Object Model.
  //   liTag.innerText = 'Cherry';
  //   liTag.append()
  //   console.log(liTag);
  //   document.querySelector('#list').append(liTag);
  // });
  // javascript 객체 vs. jQuery 객체.
  $(document).ready(function(){ // DOM code 다 실행 후 표시
    // //  let elem = jQuery('<li />');
    // let elem = $('<li />');
    // //  elem.innerText : 에러.
    // elem.text('Cherry');
    // console.log(elem);
    $('#list').append($('<li />').text('Cherry'),//
                      $('<li />').text('Mango'));
  })