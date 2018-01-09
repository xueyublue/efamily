/**
 * common-methods.js
 * 
 * Created by DARREN on 05-DEC-2017
 * 
 */

/** Common Methods **/
// Added by DARREN on 12-DEC-2017
// Scroll page to bottom
function scrollToBottom(){ 
	$('html, body, .content').animate({scrollTop: $(document).height()}, 1000); 
	return true; 
}

// Added by DARREN on 12-DEC-2017
// Scroll page to bottom with speed
function scrollToBottom(speed){ 
	$('html, body, .content').animate({scrollTop: $(document).height()}, speed); 
	return true; 
}

// Added by DARREN on 12-DEC-2017
// Scroll page to top
function scrollToTop(){ 
	$('html, body').animate({scrollTop:0}, 'normal'); 
	return true; 
}

// Added by DARREN on 12-DEC-2017
// Scroll page to top with speed
function scrollToTop(speed){ 
	$('html, body').animate({scrollTop:0}, speed); 
	return true; 
}

// Added by DARREN on 13-DEC-2017
// Date Formatter
function dateFormatter(format) {
	// Overwrite date prototype
	Date.prototype.format = function(fmt) { 
	     var o = { 
	        "M+" : this.getMonth()+1,                 
	        "d+" : this.getDate(),                    
	        "h+" : this.getHours(),                   
	        "m+" : this.getMinutes(),                 
	        "s+" : this.getSeconds(),                 
	        "q+" : Math.floor((this.getMonth()+3)/3), 
	        "S"  : this.getMilliseconds()             
	    }; 
	    if(/(y+)/.test(fmt)) {
	            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	    }
	     for(var k in o) {
	        if(new RegExp("("+ k +")").test(fmt)){
	             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	         }
	     }
	    return fmt; 
	} 
	
	return new Date().format(format);
}

// Added by DARREN on 19-DEC-2017
// Set cookie
function setCookie(name, value, day){
    var date = new Date();
    date.setDate(date.getDate() + day);
    document.cookie = name + '=' + value + ';expires='+ date;
};

// Added by DARREN on 19-DEC-2017
// Get cookie
function getCookie(name){
    var reg = RegExp(name+'=([^;]+)');
    var arr = document.cookie.match(reg);
    if(arr){
        return arr[1];
    } else{
        return '';
    }
};

// Added by DARREN on 19-DEC-2017
// Delete cookie
function delCookie(name){
    setCookie(name, null, -1);
};


// Added by DARREN on 09-JAN-2018
// Check if string end with string
function endWith(str, suffix) {
	String.prototype.endWith=function(endStr){
	  var d=this.length-endStr.length;
	  return (d>=0&&this.lastIndexOf(endStr)==d)
	}
	
	return str.endWith(suffix);
}

/** Project Specific Methods **/

