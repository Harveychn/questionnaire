var localData = [{name:'小明',height:'180',weight:'70'},{name:'小王',height:'178',weight:'66'},{name:'小王',height:'178',weight:'66'}];
var addPerson = document.getElementById('addPerson');
var changeTableAll = document.getElementById('changeTableAll');
var dataLength;
dataLength = localData.length;
var tableAll = document.getElementById('tableAll');

(function () {
    for(var i=0;i<dataLength;i++){
        tableAll.innerHTML+= '<tr><th>' + localData[i].name + '</th><th>' + localData[i].height + 'cm' + '</th><th>' + localData[i].weight + 'kg' +'</th><th><button name="'+ i +'" id="dataDelete'+ i +'">delete</button></th></tr>';
    }
})();
//数据增加
changeTableAll.onclick=function () {
    var nameV = addPerson.children[0].value;
    var heightV = addPerson.children[1].value;
    var weightV = addPerson.children[2].value;
    tableAll.innerHTML+= '<tr><th>' + nameV + '</th><th>' + heightV + 'cm' +'</th><th>' + weightV + 'kg' +'</th><th><button name="'+ dataLength +'" id="dataDelete'+ dataLength  +'">delete</button></tr>';
    localData.push({name:nameV,height:heightV,weight:weightV});
    dataLength = localData.length;
    console.log(localData);
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