function deleteSick(sickId) {
    if (confirm("Bạn chắc chắn xóa không?") == true) { 
        fetch(`/SpringClinic/api/sick/${sickId}`, {
            method: "delete",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.status == 200) {
                let d = document.getElementById(`sick${sickId}`);
                d.style.display = "none";
            } else 
                alert("Something wrong!!!");
        })
    }
}

function deleteAppointment(appId) {
    if (confirm("Bạn chắc chắn xóa không?") == true) { 
        fetch(`/SpringClinic/api/appointment/${appId}`, {
            method: "delete",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.status == 200) {
                let d = document.getElementById(`appointment${appId}`);
                d.style.display = "none";
            } else 
                alert("Something wrong!!!");
        })
    }
}

//function addToCart(medicineId) {
//    fetch(`/SpringClinic/api/medicine/${medicineId}`).then(res => {
//        if (res.status == 200) {
//            let d = document.getElementById("cart-counter");
//            d.innerText = parseInt(d.innerText) + 1; 
//        } else {
//            alert("Something wrong!!!");
//        }
//    })
//}
function addToCart(id, name, price) {
    event.preventDefault()
    
    fetch("/SpringClinic/api/cart", {
        method: 'post',
        body: JSON.stringify({
            "medicineId": id,
            "medicineName": name,
            "price": price,
            "quantity": 1,
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res){
        return res.json()
    }).then(function(data){
        let counter = document.getElementById("cart-counter")
        counter.innerText = data
    })
}

function updateCart(obj, medicineId){
    fetch("/SpringClinic/api/cart", {
        method: "put",
        body: JSON.stringify({
            "medicineId": medicineId,
            "medicineName": "",
            "price": 0,
            "quantity": obj.value
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res){
        return res.json()
    }).then(function(data){
        let counter = document.getElementById("cart-counter")
        counter.innerText = data.counter
        let amount = document.getElementById("amount-cart")
        amount.innerText = data.amount
        location.reload()
    })
}


function deleteCart(medicineId){
    if(confirm("Bạn có muốn xoá không")==true){
        fetch(`/SpringClinic/api/cart/${medicineId}`, {
        method: "delete", 
        }).then(function(res){
            return res.json()
        }).then(function(data){
            let counter = document.getElementById("cart-counter")
            counter.innerText = data.counter
            let amount = document.getElementById("amount-cart")
            amount.innerText = data.amount
            
            location.reload()
        })
    }
    
}

function pay() {
    if(confirm("Bạn có muốn xác nhận không")==true){
        fetch(`/SpringClinic/api/pay`, {
            method: "post",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function(res){
            return res.json()
        }).then(function(code){
            console.info(code);
            
            location.reload()
        })
    }
}



//
//
//function deleteSick(sickId) {
//    if (confirm("Bạn chắc chắn xóa không?") == true) { 
//        fetch(`http://springmvc-env.eba-2mrwhpvg.us-east-2.elasticbeanstalk.com/api/sick/${sickId}`, {
//            method: "delete",
//            headers: {
//                "Content-Type": "application/json"
//            }
//        }).then(res => {
//            if (res.status == 200) {
//                let d = document.getElementById(`sick${sickId}`);
//                d.style.display = "none";
//            } else 
//                alert("Something wrong!!!");
//        })
//    }
//}
//
//function deleteAppointment(appId) {
//    if (confirm("Bạn chắc chắn xóa không?") == true) { 
//        fetch(`http://springmvc-env.eba-2mrwhpvg.us-east-2.elasticbeanstalk.com/api/appointment/${appId}`, {
//            method: "delete",
//            headers: {
//                "Content-Type": "application/json"
//            }
//        }).then(res => {
//            if (res.status == 200) {
//                let d = document.getElementById(`appointment${appId}`);
//                d.style.display = "none";
//            } else 
//                alert("Something wrong!!!");
//        })
//    }
//}
//
////function addToCart(medicineId) {
////    fetch(`/SpringClinic/api/medicine/${medicineId}`).then(res => {
////        if (res.status == 200) {
////            let d = document.getElementById("cart-counter");
////            d.innerText = parseInt(d.innerText) + 1; 
////        } else {
////            alert("Something wrong!!!");
////        }
////    })
////}
//function addToCart(id, name, price) {
//    event.preventDefault()
//    
//    fetch("http://springmvc-env.eba-2mrwhpvg.us-east-2.elasticbeanstalk.com/api/cart", {
//        method: 'post',
//        body: JSON.stringify({
//            "medicineId": id,
//            "medicineName": name,
//            "price": price,
//            "quantity": 1,
//        }),
//        headers: {
//            "Content-Type": "application/json"
//        }
//    }).then(function(res){
//        return res.json()
//    }).then(function(data){
//        let counter = document.getElementById("cart-counter")
//        counter.innerText = data
//    })
//}
//
//function updateCart(obj, medicineId){
//    fetch("http://springmvc-env.eba-2mrwhpvg.us-east-2.elasticbeanstalk.com/api/cart", {
//        method: "put",
//        body: JSON.stringify({
//            "medicineId": medicineId,
//            "medicineName": "",
//            "price": 0,
//            "quantity": obj.value
//        }),
//        headers: {
//            "Content-Type": "application/json"
//        }
//    }).then(function(res){
//        return res.json()
//    }).then(function(data){
//        let counter = document.getElementById("cart-counter")
//        counter.innerText = data.counter
//        let amount = document.getElementById("amount-cart")
//        amount.innerText = data.amount
//        location.reload()
//    })
//}
//
//
//function deleteCart(medicineId){
//    if(confirm("Bạn có muốn xoá không")==true){
//        fetch(`http://springmvc-env.eba-2mrwhpvg.us-east-2.elasticbeanstalk.com/api/cart/${medicineId}`, {
//        method: "delete", 
//        }).then(function(res){
//            return res.json()
//        }).then(function(data){
//            let counter = document.getElementById("cart-counter")
//            counter.innerText = data.counter
//            let amount = document.getElementById("amount-cart")
//            amount.innerText = data.amount
//            
//            location.reload()
//        })
//    }
//    
//}
//
//function pay() {
//    if(confirm("Bạn có muốn xác nhận không")==true){
//        fetch(`http://springmvc-env.eba-2mrwhpvg.us-east-2.elasticbeanstalk.com/api/pay`, {
//            method: "post",
//            headers: {
//                "Content-Type": "application/json"
//            }
//        }).then(function(res){
//            return res.json()
//        }).then(function(code){
//            console.info(code);
//            
//            location.reload()
//        })
//    }
//}


