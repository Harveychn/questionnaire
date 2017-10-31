var changeTableAll = document.getElementById('ok');
var title = document.getElementById('title');
var objects = document.getElementById('id_select');
var tableAll = document.getElementById('tableAll');

//数据增加
changeTableAll.onclick=function () {
    var titleV = title.value;
    var objectsV = objects.value;
    tableAll.innerHTML+= '<tr><th>' + '1' + '</th><th>' +titleV +'</th><th>' + 'time'  +'</th><th>' +objectsV+'</th><th><button name="'+ dataLength +'" id="dataDelete'+ dataLength  +'">delete</button></tr>';
    
};
//数据删除
document.getElementById('tableAll').addEventListener('click',function () {
    var ev = ev || window.event;
    var target = ev.target || ev.srcElement;
    if(target.nodeName.toLocaleLowerCase() == 'button'){
        target.parentNode.parentNode.remove();
        localData[target.name] = null;
        console.log(localData);
    }
});