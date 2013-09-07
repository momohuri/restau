window.restau.tmpl = window.restau.tmpl || {};
restau.tmpl.cockpit = restau.tmpl.cockpit || {};
restau.tmpl.cockpit.menu = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='<div id="menu">\n            <span id="logoAdmin">\n                <img src="/css/images/logoResto.png" width="300px" alt="logo"/>\n            </span>\n\n    <div id="bt_menu_superadmin">\n    </div>\n</div>\n\n\n<div id="sous_menu">\n    <nav id="nav_menu">\n        <ul>\n            ';
 sections.forEach(function(section){ 
__p+='\n            <li><a class="toSection" data-id="'+
((__t=( section.get('id') ))==null?'':__t)+
'">'+
((__t=( section.get('name') ))==null?'':__t)+
'</a></li>\n            ';
 }); 
__p+='\n            <li><a href="createNewSection">createNewSection</a></li>\n        </ul>\n    </nav>\n</div>';
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
