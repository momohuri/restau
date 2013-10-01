window.restau.tmpl = window.restau.tmpl || {};
restau.tmpl.cockpit = restau.tmpl.cockpit || {};
restau.tmpl.cockpit.menu = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='\n<div class="navbar navbar-inverse navbar-fixed-top">\n    <div class="container">\n        <div class="navbar-header">\n            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">\n                <span class="icon-bar"></span>\n                <span class="icon-bar"></span>\n                <span class="icon-bar"></span>\n            </button>\n            <a class="navbar-brand" href="#">Restau</a>\n        </div>\n        <div class="collapse navbar-collapse">\n            <ul class="nav navbar-nav">\n                <li class="active"><a href="#">Home</a></li>\n                <li><a href="#about">About</a></li>\n                <li><a href="#contact">Contact</a></li>\n            </ul>\n        </div><!--/.nav-collapse -->\n    </div>\n</div>\n\n\n<div id="menu">\n    <nav id="nav_menu">\n        <ul>\n            ';
 sections.forEach(function(section){ 
__p+='\n            <li><a class="toSection" data-id="'+
((__t=( section.get('id') ))==null?'':__t)+
'">'+
((__t=( section.get('name') ))==null?'':__t)+
'</a></li>\n            ';
 }); 
__p+='\n            <li><a id="createNewSection">createNewSection</a></li>\n        </ul>\n    </nav>\n</div>\n\n\n\n<div id="content"></div>';
}
return __p;
};
restau.tmpl.cockpit.newMenu = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='<div id="formulaire_menu">\n    <h1 class="h1Admin">Creation of a new Section</h1>\n\n    <form>\n        <div id="nomMenu">\n            <input type="text" name=\'name\' placeholder="Section Name"></div>\n        <table>\n\n            <tr>\n                <td>\n                    <fieldset>\n                        <legend align="center">Items</legend>\n                        <table id=\'Items\'>\n                            <tr>\n                                <th>Name</th>\n                                <th>Description</th>\n                                <th>Calories</th>\n                                <th> Price</th>\n                                <th> Spicy</th>\n                                <th> Vegan</th>\n                                <th> Enable</th>\n                            </tr>\n                            <tr id=\'item0\'>\n                                <td><input type=\'text\' name=\'name\' placeholder="Name" maxlength="25" minlength="4"\n                                           required></td>\n                                <td><input type=\'text\' name=\'description\' placeholder="Description"></td>\n                                <td><input type=\'text\' name=\'calories\' placeholder="Calories"></td>\n                                <td><input type=\'number\' name=\'price\' placeholder=\'0\' required></td>\n                                <td>\n                                    <select name="spicy">\n                                        <option value="0">0</option>\n                                        <option value="1">1</option>\n                                        <option value="2">2</option>\n                                        <option value="3">3</option>\n                                    </select>\n                                </td>\n                                <td><select name="vegetarian">\n                                    <option value="yes">yes</option>\n                                    <option value="no" selected="selected">no</option>\n                                </select></td>\n                                <td><select name="enable">\n                                    <option value="yes">yes</option>\n                                    <option value="no">no</option>\n                                </select></td>\n                            </tr>\n                        </table>\n\n\n                    </fieldset>\n\n                    <div class="bt_ajout_sup">\n                        <a id=\'addItem\'><img src="/css/images/admin/plus.png"></a>\n                        <a id=\'deleteItem\'><img src="/css/images/admin/-.png"></a>\n                    </div>\n                </td>\n            </tr>\n        </table>\n        <input type=\'submit\' name=\'addMenu\' value="Submit" class="bt">\n    </form>\n</div>\n\n';
}
return __p;
};
restau.tmpl.cockpit.sectionEdit = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='<table>\n\n    <tr>\n        <td>\n            <fieldset>\n                <legend align="center">Items</legend>\n                <table id=\'Items\'>\n                    <tr>\n                        <th>Name</th>\n                        <th> Price</th>\n                        <th> Enable</th>\n                        <th> Edit</th>\n                        <th> Delete</th>\n                    </tr>\n                    ';
 items.forEach(function(item){ 
__p+='\n                    <tr data-id="'+
((__t=( item.get('id') ))==null?'':__t)+
'">\n                        <td>'+
((__t=( item.get('name')))==null?'':__t)+
'\n                        </td>\n                        <td>\n                            <input type=\'number\' name=\'price\' placeholder=\'0\' required value="'+
((__t=( item.get('price')))==null?'':__t)+
'">\n                        </td>\n                        <td><select name="enable">\n                            <option value="yes">yes</option>\n                            <option value="no" ';
 if(item.get('enable')===0){ "selected" } 
__p+=' >no</option>\n                        </select></td>\n                        <td><input type="button" value="edit" class="edit"> </td>\n                        <td><input type="button" value="delete" class="delete"><td>\n                    </tr>\n                    ';
 }); 
__p+='\n                </table>\n\n\n            </fieldset>\n\n        </td>\n    </tr>\n</table>\n\n        <form id="newItem">\n\n            <label for="name">Name: </label> <input type=\'text\' name=\'name\' placeholder="Name" maxlength="25" minlength="4" required value="'+
((__t=( item.get('name') ))==null?'':__t)+
'">\n            <label for="description">description: </label> <input type=\'text\' name=\'description\' placeholder="Description" value="'+
((__t=( item.get('description') ))==null?'':__t)+
'">\n            <label for="calories">calories: </label> <input type=\'text\' name=\'calories\' placeholder="Calories"  value="'+
((__t=( item.get('calories') ))==null?'':__t)+
'">\n            <label for="price">price: </label> <input type=\'number\' name=\'price\' placeholder=\'0\' required value="'+
((__t=( item.get('price') ))==null?'':__t)+
'">\n\n            <label for="spicy">spicy: </label>   <select name="spicy"> <option value="0">0</option><option value="1">1</option><option value="2">2</option> <option value="3">3</option></select>\n\n            <label for="vegetarian">vegetarian: </label> <select name="vegetarian"> <option value="yes">yes</option><option value="no" selected="selected">no</option></select>\n            <input type="submit" value="submit">\n        </form>';
}
return __p;
};
restau.tmpl.cockpit.sectionNew = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='<form id="createNewSectionForm">\n    <label for="name">Name : </label>\n    <input id="name" name="name" type="text" placeholder="Section Name">\n\n\n\n    <input type="submit" value="submit">\n</form>\n\n\n';
}
return __p;
};
