/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//function addToCart(medicineId) {
//    fetch(`/SpringClinic/api/cart/${medicineId}`).then(res => res.json()).then(data=>{
//        let d = document.getElementById("cart-counter");
//        if (d !== null) {
//            d.innerText = data;
//        }
//    })
//}

function addToCart(medicineId) {
    fetch(`/SpringClinic/api/medicine/${medicineId}`).then(res => {
        if (res.status == 200) {
            let d = document.getElementById("cart-counter");
            d.innerText = parseInt(d.innerText) + 1; 
        } else {
            alert("Something wrong!!!");
        }
    })
}

function pay() {
    fetch(`/SpringClinic/api/pay`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => {
        if (res.status == 200) {
            location.reload();
        } else {
            alert("Something wrong!!!");
        }
    })
}


