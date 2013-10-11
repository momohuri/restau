window.restau.tmpl = window.restau.tmpl || {};
restau.tmpl.cockpit = restau.tmpl.cockpit || {};
restau.tmpl.cockpit.navigation = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='<div class="navbar navbar-inverse navbar-fixed-top menuBasedOnName">\n    <div class="container">\n        <div class="navbar-header">\n            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">\n                <span class="icon-bar"></span>\n                <span class="icon-bar"></span>\n                <span class="icon-bar"></span>\n            </button>\n            <a class="navbar-brand" href="#">Restau</a>\n        </div>\n        <div class="collapse navbar-collapse">\n            <ul class="nav navbar-nav">\n                <li class="dropdown" data-reference="menu">\n                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu <b class="caret"></b></a>\n                    <ul class="dropdown-menu">\n                        ';
 menus.forEach(function(menu){ 
__p+='\n                        <li><a class="toSection" data-id="'+
((__t=( menu.get('id') ))==null?'':__t)+
'">'+
((__t=( menu.get('name') ))==null?'':__t)+
'</a></li>\n                        ';
 }); 
__p+='\n                    </ul>\n                </li>\n                <li data-reference="createNewSection"><a id="createNewSection">createNewSection</a></li>\n                <li data-reference="contact"><a href="#contact">Contact</a></li>\n            </ul>\n        </div>\n    </div>\n</div>\n\n<div id="content" class="container"></div>';
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
__p+='<div class="menuBasedOnId">\n    <ul class="nav nav-tabs">\n        ';
 sections.forEach(function(section){ 
__p+='\n        <li data-reference="'+
((__t=( section.get('id') ))==null?'':__t)+
'"><a class="toSection" data-id="'+
((__t=( section.get('id') ))==null?'':__t)+
'">'+
((__t=(
            section.get('name') ))==null?'':__t)+
'</a></li>\n        ';
 }); 
__p+='\n        <li data-reference="new"><a class="newSection">New Section</a></li>\n\n    </ul>\n</div>\n\n\n<div>\n    <table class="table">\n        <thead>\n\n        <tr>\n            <th>Name</th>\n            <th> Price</th>\n            <th> Enable</th>\n            <th> Edit</th>\n            <th> Delete</th>\n        </tr>\n        </thead>\n        <tbody>\n        ';
 items.forEach(function(item){ 
__p+='\n        <tr data-id="'+
((__t=( item.get('id') ))==null?'':__t)+
'">\n            <td>'+
((__t=( item.get('name')))==null?'':__t)+
'\n            </td>\n            <td>\n                '+
((__t=( item.get('price')))==null?'':__t)+
'\n            </td>\n            <td>\n                '+
((__t=( item.get('enable')===0 ? "no":"yes" ))==null?'':__t)+
'\n            </td>\n\n            <td><input type="button" value="edit" class="edit"></td>\n            <td><input type="button" value="delete" class="deleteItem">\n            <td>\n        </tr>\n        ';
 }); 
__p+='\n        </tbody>\n    </table>\n\n\n    <form id="newItem" role="form" class="form-horizontal">\n        <input type="hidden" name="id" value="'+
((__t=( item.get('id') ))==null?'':__t)+
'">\n\n        <div class="form-group">\n            <label class="col-lg-2 control-label" for="name">Name: </label>\n\n            <div class="col-lg-2">\n                <input class="form-control" type=\'text\' name=\'name\' placeholder="Name" maxlength="25" minlength="4"\n                       required value="'+
((__t=( item.get('name') ))==null?'':__t)+
'">\n            </div>\n        </div>\n        <div class="form-group">\n            <label class="col-lg-2 control-label" for="description">Description: </label>\n\n            <div class="col-lg-4">\n                <input type=\'text\' name=\'description\' class="form-control"\n                       placeholder="Description"\n                       value="'+
((__t=( item.get('description') ))==null?'':__t)+
'"></div>\n        </div>\n        <div class="form-group">\n\n            <label class="col-lg-2 control-label" for="calories">Calories: </label>\n\n            <div class="col-lg-2">\n                <input type=\'text\' name=\'calories\' placeholder="Calories" class="form-control"\n                       value="'+
((__t=( item.get('calories') ))==null?'':__t)+
'"></div>\n        </div>\n        <div class="form-group">\n            <label class="col-lg-2 control-label" for="price">Price: </label>\n\n            <div class="col-lg-2">\n                <input type=\'number\' name=\'price\' placeholder=\'0\' required class="form-control"\n                       value="'+
((__t=( item.get('price') ))==null?'':__t)+
'"></div>\n\n        </div>\n        <div class="form-group">\n            <label class="col-lg-2 control-label" for="spicy">Spicy: </label>\n\n            <div class="col-lg-2">\n                <select name="spicy" class="form-control">\n                    <option value="0">0</option>\n                    <option value="1">1</option>\n                    <option value="2">2</option>\n                    <option value="3">3</option>\n                </select>\n            </div>\n\n            <label class="col-lg-2 control-label" for="vegetarian">Vegetarian: </label>\n\n            <div class="col-lg-2">\n                <select name="vegetarian" class="form-control">\n                    <option value="yes">yes</option>\n                    <option value="no" selected="selected">no</option>\n                </select>\n            </div>\n\n        </div>\n        <div class="form-group">\n            <label class="col-lg-2 control-label" for="enable">Enable: </label>\n\n            <div class="col-lg-2">\n                <select name="enable" class="form-control">\n                    <option value="0">no</option>\n                    <option value="1">yes</option>\n                </select>\n            </div>\n        </div>\n        <div class="form-group">\n            <div class="col-lg-offset-2 col-lg-10">\n                <button type="submit" class="btn btn-default">Submit</button>\n            </div>\n        </div>\n    </form>\n</div>';
}
return __p;
};
restau.tmpl.cockpit.sectionNew = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='<div class="menuBasedOnId">\n    <ul class="nav nav-tabs">\n        ';
 sections.forEach(function(section){ 
__p+='\n        <li data-reference="'+
((__t=( section.get('id') ))==null?'':__t)+
'"><a class="toSection" data-id="'+
((__t=( section.get('id') ))==null?'':__t)+
'">'+
((__t=(
            section.get('name') ))==null?'':__t)+
'</a></li>\n        ';
 }); 
__p+='\n        <li data-reference="new"><a class="newSection">New Section</a></li>\n\n    </ul>\n</div>\n\n\n\n<form id="createNewSectionForm">\n    <label for="name">Name : </label>\n    <input id="name" name="name" type="text" placeholder="Section Name">\n\n\n    <input type="submit" value="submit">\n</form>\n\n\n';
}
return __p;
};
