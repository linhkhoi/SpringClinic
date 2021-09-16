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


