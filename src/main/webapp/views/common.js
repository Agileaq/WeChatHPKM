/**
 * 
 */
var months=["Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug","Sep","Oct", "Nov", "Dec"];
function getParameter(name){
	var str=location.search;
	var idx=str.indexOf(name);
	var endIdx=str.indexOf('&',idx);
	if(endIdx==-1){
		endIdx=str.length;
	}
	return str.substring(idx+name.length+1,endIdx);
}
function formatDate(time, showTimeOnly, hideTime){
	if(!time){
		return "";
	}
	if(typeof time=='number'){
		var d=new Date(time);
		if(showTimeOnly){
			return d.getHours+":"+d.getMinutes();
		}
		
		if(!hideTime){
			return months[d.getMonth()]+' '+d.getDate()+' '+d.getFullYear();
		}else{
			return months[d.getMonth()]+' '+d.getDate()+' '+d.getFullYear()+" "+d.getHours+":"+d.getMinutes();
		}
	}else{
		var str=time.split('-');
		if(str[1].indexOf('0')==0){
			str[1]=str[1].substring(1);
		}
		return months[parseInt(str[1])-1]+" "+str[2]+" "+str[0];
	}
}
function showSysMessage(message){
	$('#sys-msg').html(message);$('#sys-msg').fadeIn(400);
	setTimeout(function(){
		$('#sys-msg').fadeOut(400);
	}, 2000);
}

if (!Array.prototype.filter){
  Array.prototype.filter = function(fun /*, thisp*/)
  {
    var len = this.length;
    if (typeof fun != "function"){
      throw new TypeError();
    }
    var res = new Array();
    var thisp = arguments[1];
    for (var i = 0; i < len; i++){
      if (i in this){
        var val = this[i]; // in case fun mutates this
        if (fun.call(thisp, val, i, this)){
          res.push(val);
        }
      }
    }
    return res;
  };
}