define([], function () {window.restau = window.restau || {apps: {},cockpit:{  views: {  },  models: {  },  collections: {  },  routers: {  }},client:{  views: {  },  models: {  },  collections: {  },  routers: {  }}};
            window.restau.tmpl = window.restau.tmpl || {};
restau.tmpl.client = restau.tmpl.client || {};
restau.tmpl.client.menu = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='<div id="boxedWrapper">\n\n<header id="top">\n\n    <div class="container">\n        <div class="row-fluid">\n            <div class="span12">\n                <h4>Restau !</h4>\n                <nav class="short-contact pull-right">\n                    <ul class="wheel-group">\n                        <li><p>Some info about the restaurant</p></li>\n                    </ul>\n                    <div class="line"></div>\n                </nav>\n\n                <div class="clearfix"></div>\n            </div>\n        </div>\n    </div>\n\n</header>\n\n<section id="content">\n\n<div class="container">\n<div class="row-fluid">\n<div class="span12">\n\n<section id="menucard">\n\n<div class="filter">\n    <ul id="menucard-tabs">\n        ';
 sections.forEach(function(section,i){
__p+='\n        <li><a calss=\'goToSection\' data-name="'+
((__t=( section.get('name') ))==null?'':__t)+
'">'+
((__t=( section.get('name') ))==null?'':__t)+
'</a></li>\n        ';
});
__p+='\n    </ul>\n</div>\n\n<div class="tab-content">\n<div class="tab-pane active" id="tab-breakfast">\n\n<div class="row-fluid"> <!-- menucards -->\n\n    ';
 sections.forEach(function(section,i){
__p+='\n    <div class="span6">\n        <article class="menucard">\n            <header class="sectionTitle">\n                <h3>'+
((__t=( section.get('name') ))==null?'':__t)+
'</h3>\n            </header>\n            <section class="dishes">\n                <ul>\n                    ';
 section.get('items').forEach(function(item){ 
__p+='\n                    <li>\n                        <div class="image">\n\n                        </div>\n                        <div class="data">\n                            <h4>'+
((__t=( item.name ))==null?'':__t)+
'</h4>\n\n                            <p>\n                                '+
((__t=( item.desc ))==null?'':__t)+
'\n                            </p>\n                            <span class="price">'+
((__t=( item.price + '$' ))==null?'':__t)+
'</span>\n                        </div>\n                        <div class="quantity">\n                            <select>\n                                <option value=0>0</option>\n                                <option value=1>1</option>\n                                <option value=2>2</option>\n                                <option value=3>3</option>\n                                <option value=4>4</option>\n                            </select>\n                        </div>\n                    </li>\n                    ';
})
__p+='\n                </ul>\n            </section>\n        </article>\n    </div>\n    ';
});
__p+='\n\n\n</div>\n\n<!-- //menucards -->\n</div>\n\n\n</div>\n\n\n\n\n<!--add later-->\n\n<!--<header class="text-center">-->\n<!--<h3>Are you overwhelmed? Here are our reccomendations!</h3>-->\n<!--</header>-->\n\n<!--<div class="recommendations"> &lt;!&ndash; recommendations &ndash;&gt;-->\n<!--<div class="row-fluid">-->\n<!--<div class="span6">-->\n<!--<div class="media">-->\n<!--<a class="pull-left" href="#">-->\n<!--<figure class="rounded"><img class="media-object" src="images/content-demo/img28.jpg" alt="">-->\n<!--</figure>-->\n<!--</a>-->\n\n<!--<div class="media-body">-->\n<!--<h3><a href="#">Appetizer</a></h3>-->\n\n<!--<p><strong>Donec sed odio dui.</strong> Nulla vitae elit libero, a pharetra augue. Nullam id dolor-->\n<!--id nibh ultricies vehicula ut id elit. Integer posuere erat.</p>-->\n<!--</div>-->\n<!--</div>-->\n<!--</div>-->\n<!--<div class="span6">-->\n<!--<div class="media">-->\n<!--<a class="pull-left" href="#">-->\n<!--<figure class="rounded"><img class="media-object" src="images/content-demo/img29.jpg" alt="">-->\n<!--</figure>-->\n<!--</a>-->\n\n<!--<div class="media-body">-->\n<!--<h3><a href="#">Main Dish</a></h3>-->\n\n<!--<p><strong>Donec sed odio dui.</strong> Nulla vitae elit libero, a pharetra augue. Nullam id dolor-->\n<!--id nibh ultricies vehicula ut id elit. Integer posuere erat.</p>-->\n<!--</div>-->\n<!--</div>-->\n<!--</div>-->\n<!--</div>-->\n<!--</div>-->\n<!-- //recommendations -->\n\n<!--</section>-->\n\n\n\n</section>\n<!-- //span -->\n</div>\n<!-- //row -->\n</div>\n<!-- //container -->\n</div>\n\n</section>\n\n\n<footer id="footer">\n    <div class="container">\n        <div class="row-fluid">\n            <div class="span3">\n                <p>\n                    &copy; Copyright 2013 by Gandhi && Maurin</span>\n                </p>\n\n            </div>\n            <div class="span9">\n                <nav class="short-menu">\n                    <ul>\n                        <li><a href="#">Home</a></li>\n                        <li><a href="#">Pages</a></li>\n                        <li><a href="#">Features</a></li>\n                        <li><a href="#">Blog</a></li>\n                        <li><a href="#">Gallerie</a></li>\n                        <li><a href="#">Shortcodes</a></li>\n                        <li><a href="#">Contact</a></li>\n                    </ul>\n                </nav>\n            </div>\n        </div>\n    </div>\n</footer>\n\n</div>\n\n</div>\n\n';
}
return __p;
};
restau.tmpl.cockpit = restau.tmpl.cockpit || {};
restau.tmpl.cockpit.navigation = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='<div class="navbar navbar-inverse navbar-fixed-top menuBasedOnName">\n    <div class="container">\n        <div class="navbar-header">\n            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">\n                <span class="icon-bar"></span>\n                <span class="icon-bar"></span>\n                <span class="icon-bar"></span>\n            </button>\n            <a class="navbar-brand" href="#">Restau</a>\n        </div>\n        <div class="collapse navbar-collapse">\n            <ul class="nav navbar-nav">\n                <li class="dropdown" data-reference="menu">\n                <li><a class="toSection" data-id="manageSections">Menu</a></li>\n                <li data-reference="currentOrders/"><a id="currentOrders">Current orders</a></li>\n            </ul>\n        </div>\n    </div>\n</div>\n\n<div id="content" class="container"></div>';
}
return __p;
};
restau.tmpl.cockpit.orders = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='<div class="list-group">\n\n    <div class="menuBasedOnName">\n        <ul class="nav nav-tabs">\n            <li data-reference="currentOrders"><a id="currentOrders" class="navigation">Current Orders</a></li>\n            <li data-reference="toPayOrders"><a id="toPayOrders" class="navigation">Orders waiting for payment</a></li>\n            <li data-reference="paidOrders"><a id="paidOrders" class="navigation">Orders paid</a></li>\n        </ul>\n    </div>\n\n\n    ';
orders.forEach(function(order,i){
__p+='\n    <a href="#" class="list-group-item '+
((__t=( i===1?'active':''))==null?'':__t)+
'">\n        <h4 class="list-group-item-heading">Order One</h4>\n        <span>Status :  '+
((__t=( order.get('status') ))==null?'':__t)+
'</span>\n\n        <p class="list-group-item-text"></p>\n    </a>\n    ';
})
__p+='\n\n</div>\n';
}
return __p;
};
restau.tmpl.cockpit.sectionEdit = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='<div class="menuBasedOnId">\n<div class="menuBasedOnId">\n    <ul class="nav nav-tabs">\n        ';
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
__p+='\n        <li data-reference="manage"><a class="manageSections">Manage Sections</a></li>\n\n    </ul>\n</div>\n\n\n<div>\n    <table class="table">\n        <thead>\n\n        <tr>\n            <th>Name</th>\n            <th> Price</th>\n            <th> Enable</th>\n            <th> Edit</th>\n            <th> Delete</th>\n        </tr>\n        </thead>\n        <tbody>\n        ';
 items.forEach(function(item){ 
__p+='\n        <tr data-id="'+
((__t=( item.get('id') ))==null?'':__t)+
'">\n            <td>'+
((__t=( item.get('name')))==null?'':__t)+
'\n            </td>\n            <td>\n                '+
((__t=( item.get('price')))==null?'':__t)+
'\n            </td>\n            <td>\n                '+
((__t=( item.get('enabled') ? "yes":"no" ))==null?'':__t)+
'\n            </td>\n\n            <td><input type="button" value="edit" class="edit btn"></td>\n            <td><input type="button" value="delete" class="deleteItem btn-danger">\n            <td>\n        </tr>\n        ';
 }); 
__p+='\n        </tbody>\n    </table>\n    <button class="btn btn-success" id="saveRankDisplay">Save Rank Display</button>\n\n    <form id="newItem" role="form" class="form-horizontal">\n        <input type="hidden" name="id" value="'+
((__t=( item.get('id') ))==null?'':__t)+
'">\n        <input type="hidden" name="sectionId" value="'+
((__t=( sectionId ))==null?'':__t)+
'">\n\n        <div class="form-group">\n            <label class="col-lg-2 control-label" for="name">Name: </label>\n\n            <div class="col-lg-2">\n                <input class="form-control" type=\'text\' name=\'name\' placeholder="Name" maxlength="25" minlength="4"\n                       required value="'+
((__t=( item.get('name') ))==null?'':__t)+
'">\n            </div>\n        </div>\n        <div class="form-group">\n            <label class="col-lg-2 control-label" for="description">Description: </label>\n\n            <div class="col-lg-4">\n                <input type=\'text\' name=\'desc\' class="form-control"\n                       placeholder="Description"\n                       value="'+
((__t=( item.get('desc') ))==null?'':__t)+
'"></div>\n        </div>\n        <div class="form-group">\n\n            <label class="col-lg-2 control-label" for="calories">Calories: </label>\n\n            <div class="col-lg-2">\n                <input type=\'number\' name=\'calories\' placeholder="Calories" class="form-control"\n                       value="'+
((__t=( item.get('calories') ))==null?'':__t)+
'"></div>\n        </div>\n        <div class="form-group">\n            <label class="col-lg-2 control-label" for="price">Price: </label>\n\n            <div class="col-lg-2">\n                <input type=\'number\' name=\'price\' placeholder=\'0\' required class="form-control"\n                       value="'+
((__t=( item.get('price') ))==null?'':__t)+
'"></div>\n\n        </div>\n        <div class="form-group">\n            <label class="col-lg-2 control-label" for="spicy">Spicy: </label>\n\n            <div class="col-lg-2">\n                <select name="spicy" class="form-control">\n                    <option value="0">0</option>\n                    <option value="1">1</option>\n                    <option value="2">2</option>\n                    <option value="3">3</option>\n                </select>\n            </div>\n\n            <label class="col-lg-2 control-label" for="vegetarian">Vegetarian: </label>\n\n            <div class="col-lg-2">\n                <select name="isVegetarian" class="form-control">\n                    <option value="false">no</option>\n                    <option value="true" '+
((__t=( item.get('isVegetarian')?"Selected":'' ))==null?'':__t)+
'>yes</option>\n\n                </select>\n            </div>\n\n        </div>\n        <div class="form-group">\n            <label class="col-lg-2 control-label" for="enabled">Enable: </label>\n\n            <div class="col-lg-2">\n                <select name="enabled" class="form-control">\n                    <option value="false">no</option>\n                    <option '+
((__t=( item.get('enabled')?"Selected":'' ))==null?'':__t)+
' value="true">yes</option>\n                </select>\n            </div>\n        </div>\n        <div class="form-group">\n            <div class="col-lg-offset-2 col-lg-10">\n                <button type="submit" class="btn btn-default">Submit</button>\n            </div>\n        </div>\n    </form>\n</div>';
}
return __p;
};
restau.tmpl.cockpit.sectionManage = function(obj){
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
__p+='\n        <li data-reference="manageSections"><a class="manageSections">Manage Section</a></li>\n\n    </ul>\n</div>\n\n\n<div>\n    <table class="table">\n        <thead>\n\n        <tr>\n            <th>Name</th>\n            <th> Description</th>\n            <th> Enable</th>\n            <th> Edit</th>\n            <th> Delete</th>\n        </tr>\n        </thead>\n        <tbody>\n        ';
 sections.forEach(function(section){ 
__p+='\n        <tr data-id="'+
((__t=( section.get('id') ))==null?'':__t)+
'">\n            <td>'+
((__t=( section.get('name')))==null?'':__t)+
'\n            </td>\n            <td>\n                '+
((__t=( section.get('description')))==null?'':__t)+
'\n            </td>\n            <td>\n                '+
((__t=( section.get('enabled') ? "yes":"no" ))==null?'':__t)+
'\n            </td>\n\n            <td><input type="button" value="edit" class="edit btn"></td>\n            <td><input type="button" value="delete" class="deleteItem btn-danger">\n            <td>\n        </tr>\n        ';
 }); 
__p+='\n        </tbody>\n    </table>\n    <button class="btn btn-success" id="saveRankDisplay">Save rank display</button>\n</div>\n\n<div>\n    <form id="createNewSectionForm" role="form" class="form-horizontal">\n        <input type="hidden" name="id" value="'+
((__t=( section.get('id') ))==null?'':__t)+
'">\n\n\n\n        <div class="form-group">\n\n            <label class="col-lg-2 control-label" for="name">Name: </label>\n\n            <div class="col-lg-2">\n                <input type=\'text\' name=\'name\' placeholder="Section Name" class="form-control"\n                       value="'+
((__t=( section.get('name') ))==null?'':__t)+
'">\n            </div>\n        </div>\n\n        <div class="form-group">\n\n            <label class="col-lg-2 control-label" for="description">Description : </label>\n\n            <div class="col-lg-4">\n                <input type=\'text\' name=\'description\' class="form-control"\n                       placeholder="Description" value="'+
((__t=( section.get('description') ))==null?'':__t)+
'">\n            </div>\n        </div>\n\n        <div class="form-group">\n            <label class="col-lg-2 control-label" for="enabled">Enable: </label>\n\n            <div class="col-lg-2">\n                <select name="enabled" id="enabled" class="form-control">\n                    <option value="false">no</option>\n                    <option value="true" '+
((__t=( section.get('enabled')?"selected":'' ))==null?'':__t)+
'>yes</option>\n                </select>\n            </div>\n        </div>\n\n\n        <input type="submit" class="btn" value="submit">\n    </form>\n\n</div>\n';
}
return __p;
};
    return window.restau;  });