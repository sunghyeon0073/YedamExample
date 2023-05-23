// fetchMember.js

$(function () {
  // select option 변경시.
  // $('#gender').val('Female');

  $('#list').remove();
  $('#show2').remove();
  $('#show').empty();
  $('#show').before('<h3>회원목록</h3>');

  // json 데이터 출력.
  fetch('MOCK_DATA.json')
    .then(function (resolve) {
      return resolve.json(); // stream -> object 변경.
    })
    .then(makeList)
    .catch(function (err) {
      console.error(err);
    }) // end of fetch().

$('button:contains(변경)').on('click',updateMemberFnc);

  function updateMemberFnc(e){
    let id = $('#id').val();
    let fname=$('#fname').val();
    let lname=$('#lname').val();
    let gender=$('#gender').val();
    
    let newTr=$('.template').clone();
    newTr.find('td:eq(0)').text(id);
    newTr.find('td:eq(1)').text(fname);
    newTr.find('td:eq(2)').text(lname);
    newTr.find('td:eq(3)').text(gender);
    newTr.find('td:eq(4)>button').on('click',delMember);
    newTr.removeAttr('class');

    let targetTr = $('#memberList tr:contains('+id+')');
    targetTr.replaceWith(newTr);
  }

  // 등록버튼 이벤트 추가.
  $('button:contains(등록)').on('click', addMemberFnc);

  // tr에 마우스 오버가 되면 등록화면에 출력이 되도록.
  // 라이브이벤트..
  $('body').on('dblclick', '#memberList tr', function () {
    console.log('tr click.');
    // 새로운 tr을 생성.
    let oldTr = $(this);
    let oldId = $(this).children().eq(0).text();
    let oldFname = $(this).children().eq(1).text();
    let oldLname = $(this).children().eq(2).text();
    let oldGender = $(this).children().eq(3).text();

    let newTr = $('<tr />').append(
      $('<td />').text(oldId),
      $('<td />').append($('<input />').val(oldFname)),
      $('<td />').html('<input type="text" value="' + oldLname + '">'),
      $('<td />').html(oldGender),
      $('<td />').append($('<button />').text('수정').on('click', updateTr)),
      $('<td />').append($('<input />').attr('type', 'checkbox'))
    );
    console.log(oldTr);
    oldTr.replaceWith(newTr);
  })

  // tr이동.
  $('button:eq(2)').on('click', function () {
    let targetTr = $('input[type="checkbox"]:checked').closest('tr');
    let nextTr = targetTr.next();
    console.log(nextTr.length)
    if (nextTr.length) {
      nextTr.after(targetTr);
    } else {
      console.log($('#memberList').children().first().before(targetTr))
    }
    console.log()
  })

  function updateTr() {
    let oldTr = $(this).parentsUntil('tbody');

    let id = oldTr.find('td:eq(0)').text();
    let fname = oldTr.find('td:eq(1)>input').val()
    console.log(fname);
    let newTr = $('.template').clone();
    newTr.find('td:eq(0)').text(id);
    newTr.find('td:eq(1)').text(fname);
    newTr.removeClass('template');

    console.log(newTr);
    oldTr.replaceWith(newTr);

  }

  function addMemberFnc() {
    if (!$('#id').val() || !$('#fname').val() || !$('#lname').val()) {
      alert('필수값 입력.');
      return;
    }

    // 사용자 입력값을 체크. 목록의 제일 마지막위치에 추가.
    let tr = $('<tr />').append( 
      $('<td />').text($('#id').val()),
      $('<td />').text($('#fname').val()),
      $('<td />').text($('#lname').val()),
      $('<td />').text($('#gender').val()),
      // 버튼작성.
      $('<td />').append(
        $('<button>삭제</button>').on('click', delMember)
      ),
      $('<td />').append($('<input />').attr('type', 'checkbox'))
    );
    $('#memberList').append(tr); // 목록에 추가.
    $('input[type="text"]').val(''); // 입력값 지워주기.

  }

  function makeList(result) {
    console.log(result);
    let tbl = $('<table border="1" />');
    let tbd = $('<tbody />').attr('id', 'memberList');
    result.forEach(function (member, idx) {
      if (idx < 5) { // 전체목록 중 5개만 출력.
        let tr = $('<tr />').append( //
          $('<td />').text(member.id),
          $('<td />').text(member.first_name),
          $('<td />').text(member.last_name),
          $('<td />').text(member.gender),
          // 버튼작성.
          $('<td />').append(
            $('<button>삭제</button>').on('click', delMember)
          ),
          // 체크박스.
          $('<td />').append(
            $('<input />').attr('type', 'checkbox')
          )
        );
        tbd.append(tr); // tbody에 tr 부착.
      }
    });
    tbl.append(tbd);
    $('#show').append(tbl); // <div id="show"><table border='1'>...</table></div>
    makeHead();
  }

  function makeHead() {
    // title 등록.
    $('table:eq(1)').prepend(
      $('<thead />').append($('<th />').text('ID'),
        $('<th />').text('이름'),
        $('<th />').text('성씨'),
        $('<th />').text('성별'),
        $('<th />').text('삭제'), //
        $('<th />').html('<input type="checkbox">') //
      )
    );
  }
  // 한라인 삭제.
  function delMember(e) {
    $(e.target).parentsUntil('tbody').remove();
  }
})