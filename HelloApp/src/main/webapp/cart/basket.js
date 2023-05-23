export let basket = {
    totalCount: 0,
    totalPrice: 0,
    
    delCheckedItem: function () {
        let cklist = document.querySelectorAll('input[type=checkbox]');
        let realck = [];
        for(let i = 0 ; i < cklist.length ; i++){
         if(cklist[i].checked == true){
            realck.push(cklist[i]);
         }
      }
      for(let i of realck){
         i.parentElement.parentElement.parentElement.remove();
      }
        
    },
    delAllItem: function () {
        let datas = document.querySelectorAll('.row.data');
        for(let i of datas){
         i.remove();
      }
    },
    delItem: function (thing) {
      thing.parentElement.parentElement.parentElement.remove();
        
    },
    reCalc: function () {
      /*  console.log('reCalc');*/
        this.totalCount = 0;
        this.totalPrice = 0;
    },
    updateUI: function () {
        /*console.log('updateUI');*/
        document.querySelector('#sum_p_num').textContent = '상품개수: ' + this.totalCount.formatNumber() + '개'
        document.querySelector('#sum_p_price').textContent = '합계금액: ' + this.totalPrice.formatNumber() + '원'
    },
    changePNum: function (pos,thing) {
      let up = document.querySelector('div:nth-of-type('+(pos+1)+') .updown').querySelector('span:nth-of-type(1)');
      if(thing == up){
         document.querySelector('div:nth-of-type('+(pos+1)+') .updown').children[0].value++;
         thing.parentElement.parentElement.nextElementSibling.innerText = (document.querySelector('div:nth-of-type('+(pos+1)+') .updown').children[0].value * cartItems[pos-2].price).formatNumber() + "원";
         
      }else{
         document.querySelector('div:nth-of-type('+(pos+1)+') .updown').children[0].value--;
         thing.parentElement.parentElement.nextElementSibling.innerText = (document.querySelector('div:nth-of-type('+(pos+1)+') .updown').children[0].value * cartItems[pos-2].price).formatNumber() + "원";
         if(document.querySelector('div:nth-of-type('+(pos+1)+') .updown').children[0].value< 1){
            alert('1개 이하의 수량은 선택할 수 없습니다')
            document.querySelector('div:nth-of-type('+(pos+1)+') .updown').children[0].value =1;
            thing.parentElement.parentElement.nextElementSibling.innerText = cartItems[pos-2].price.formatNumber()+"원";
         }
      }
      let pnum = document.querySelectorAll('.p_num');
      let sum = 0;
      this.totalCount =0;
      for(let i =1 ;i<pnum.length ; i++){
         this.totalCount += Number(pnum[i].value);
         sum += Number(pnum[i].value)*cartItems[i-1].price;
      }
      document.querySelector('#sum_p_num').innerText = '상품갯수 :'+this.totalCount+'개';
      document.querySelector('#sum_p_price').innerText = sum.formatNumber()+"원";
      
    },
    cartList: function () {
        cartItems.forEach((item, idx) => {
            let template = document.querySelector('#template>div.row.data').cloneNode(true);
            template.querySelector('.img>img').setAttribute('src', '../img/' + item.image)
            template.querySelector('.pname>span').textContent = item.productNm
            template.querySelector('.basketprice>input').value = item.price
            template.querySelector('.basketprice').childNodes[2].textContent = item.price.formatNumber() + "원"
            template.querySelector('.updown>input').value = item.qty
            template.querySelector('.updown>input').setAttribute('value', item.qty)
            template.querySelector('.updown>input').setAttribute('id', 'p_num' + (idx + 1));
            template.querySelector('.sum').textContent = (item.price * item.qty).formatNumber() + "원"

            document.querySelector('#basket').append(template)
        })
    }
};

var cartItems = [{
        no: 1,
        productNm: '이것이 민트다.',
        qty: 2,
        price: 12000,
        image: 'item1.PNG'
    },
    {
        no: 2,
        productNm: '와 아이스크림.',
        qty: 1,
        price: 22000,
        image: 'item2.PNG'
    },
    {
        no: 3,
        productNm: '모나카 케익.',
        qty: 1,
        price: 13600,
        image: 'item3.PNG'
    }
]

// 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function () {
    if (this == 0) return 0;
    let regex = /(^[+-]?\d+)(\d{3})/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};