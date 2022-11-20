(async function() {
    const $ = document.querySelector.bind(document);
    const formatNumber = new Intl.NumberFormat();
    const active = 'active';
    let paging;
    let temp, str, str1;
    let listState, listBill;

    function getData(path, callback)
    {
        return fetch(path)
               .then(response => response.json())
               .then(data => callback(data));
    }

    function postData(path, data, callback, method = 'POST') {
        return fetch(path, {
                    'method' : method,
                    'headers' : {
                        'Content-Type' : 'application/json'
                    },
                    'body' : JSON.stringify(data)
                })
                .then(response => response.json())
                .then(data => callback(data));
    }

    function deleteData(path, callback) {
        return fetch(path, {
                    'method' : 'DELETE',
                })
                .then(response => response.json())
                .then(data => callback(data));
    }

    //Functions Common
    function handleClass(element, action, value = active) {
        if(action == 'add') {
            element.classList.add(value);
        }
        else if(action == 'remove') {
            element.classList.remove(value);
        }
        else if(action == 'toggle') {
            element.classList.toggle(value);
        }
    }

    function getParentBySelector(element, selector)
    {
        if(element.matches(selector)) { return element; }
        var temp = element;
        do {
            temp = temp.parentElement;
        } while(!temp.matches(selector));
        return temp;
    }

    function renderUIListBill(data) {
        str = '';
        data.forEach((bill, index) => {
            temp = listState.find(state => state.id == bill.stateId).name;
            str += `
                    <tr data-key="${bill.id}">
                         <td><input type="checkbox" ></td>
                         <td>${index + 1}</td>
                         <td>${bill.code}</td>
                         <td>${bill.nameCustomer}</td>
                         <td>${formatNumber.format(bill.importMoney)}đ</td>
                         <td>${bill.strDate}</td>
                         <td>${temp}</td>
                         <td class="tbCenter">
                             <span class="td__action td__action--detail">
                                 <i class="fa-solid fa-circle-info"></i>
                             </span>

                             <span class="td__action td__action--delete">
                                 <i class="fa-solid fa-trash"></i>
                             </span>
                         </td>
                     </tr>`;
        });
        $('.listTable tbody').innerHTML = str;
    }

    // Call API

    // Call API get List State
    await getData(`/api-cart?action=listBillState`, data => {
        listState = data;
        // Active: .active
        str = `<div class="sort__item active" data-key="0">
                 <i class="fa-solid fa-check"></i>
                 <span>Tất cả đơn hàng</span>
             </div>`;
        listState.forEach(state => {
            str += `
                    <div class="sort__item" data-key="${state.id}">
                         <i class="fa-solid fa-check"></i>
                         <span>${state.name}</span>
                     </div>`;
        });
        $('.sort__list').innerHTML = str;
    });

    // Call API get ListBill
    getData(`/api-cart?action=listCart`, data => {
        listBill = data;
        renderUIListBill(data);
    });

    //handle Event
    new listTable('#list_contain', function(data) {
        const action = data.action;
        if(action == 'detail') {
            window.location = `/admin-bill?action=billDetail&billId=${data.data}`;
        }
        if(action == 'delete') {
            let agree = confirm('Bạn muốn xóa dữ liệu?');
            if(!agree) { return; }

            const ids = encodeURIComponent(JSON.stringify(data.data));
            deleteData(`/api-cart?action=deleteAll&ids=${ids}`, data => {
                if(data == true) {
                    alert('Xóa thành công!');
                    window.location.reload();
                }
                else {
                    alert('Đã xảy ra lỗi. Không thể xóa dữ liệu!');
                }
            });
        }
    });

    $('.sort').addEventListener('click', function({target:element}) {
        if(element.closest('.sort__header')) {
            handleClass(this,'toggle');
        }
        else if(element.closest('.sort__item')) {
           temp = getParentBySelector(element,'.sort__item');
           this.querySelectorAll('.sort__item').forEach(ele => {
                if(ele == temp) {
                    handleClass(ele,'add');
                }
                else {
                    handleClass(ele,'remove');
                }
           });

           temp = parseInt(temp.dataset.key);
           if(temp == 0) {
                renderUIListBill(listBill);
           }
           else {
                renderUIListBill(listBill.filter(bill => bill.stateId == temp));
           }

           handleClass(this,'remove');
        }
    });


 })();
