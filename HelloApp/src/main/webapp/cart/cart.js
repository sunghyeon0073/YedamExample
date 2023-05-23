import {
    basket
} from './basket.js';

document.addEventListener('DOMContentLoaded', function () {

    basket.cartList();
    // 장바구나 삭제 클릭.
      document.querySelectorAll('.basketcmd input').forEach(function (item) {
        item.addEventListener('click', function () {
           basket.delItem(this);
        })
    })
    // 선택상품 삭제.
    document.querySelector('.basketrowcmd a:first-child').addEventListener('click', function () {
        basket.delCheckedItem();
    })

    // 장바구니 비우기.
    document.querySelector('.basketrowcmd a:nth-child(2)').addEventListener('click', function () {
        basket.delAllItem();
    })


    // 수량변경 - 이벤트 종류 구분.
    window.onload=function(){
      document.querySelectorAll('.updown').forEach(function (item, idx) {
        item.querySelector('input').addEventListener('keyup', function () {
            basket.changePNum(idx + 1);
        })
        item.children[1].addEventListener('click', function () {
            basket.changePNum(idx + 1,this);
        })
        item.children[2].addEventListener('click', function () {
            basket.changePNum(idx + 1,this);
        })
    })
    }

    // anchor : 스크롤 탑 차단.
    document.querySelectorAll('a[href="#"]').forEach(function (item) {
        item.setAttribute('href', 'javascript:void(0)');
    })
    
    
})