//function getCategoryName(categoryID, categories) {
//    for (var i = 0; i < categories.length; i++) {
//        if (categories[i].id === categoryID) {
//            return categories[i].name;
//        }
//    }
//    return "Không xác định";
//}

function deleteBus(url, busID) {
    fetch(url, {
        method: 'delete'
    }).then(res => {
        if (res.status === 204)
            location.reload();
        else
            alert("ERROR");
    });
}


