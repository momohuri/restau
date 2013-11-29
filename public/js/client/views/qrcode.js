define(['namespace', './base-view'], function (App, BaseView, undefined) {

    App.client.views.qrcode = BaseView.extend({

        el: 'body',

        template: App.tmpl.client.qrcode,


        events: {
            'change #mypic': 'fileReader'
        },

        initialize: function () {
            var that = this;

            _.bindAll(this, 'render');

            this.render();

        },

        render: function (e) {
            this.$el.html(this.template());

        },

        fileReader: function (e) {


            var preview = document.querySelector('img');
            var file = e.currentTarget.files[0];
            var reader = new FileReader();

            var canvas = document.createElement('canvas');
            var ctx = canvas.getContext("2d");

            reader.onload = function () {

                var img = new Image();
                img.src = reader.result;
                img.onload = function () {
                    var maxWidth = 320,
                        maxHeight = 700,
                        imageWidth = img.width,
                        imageHeight = img.height;

                    if (imageWidth > imageHeight) {
                        if (imageWidth > maxWidth) {
                            imageHeight *= maxWidth / imageWidth;
                            imageWidth = maxWidth;
                        }
                    } else {
                        if (imageHeight > maxHeight) {
                            imageWidth *= maxHeight / imageHeight;
                            imageHeight = maxHeight;
                        }
                    }
                    canvas.width = imageWidth;
                    canvas.height = imageHeight;

                    ctx.drawImage(this, 0, 0, imageWidth, imageHeight);

                    // The resized file ready for upload
                    var finalFile = canvas.toDataURL("image/jpeg");
                    var request = $.ajax({
                        url: "test",
                        type: "POST",
                        data: finalFile
                    }).done(function () {
                            debugger
                        });
                    request.done(function (msg) {
                        $("#log").html(msg);
                    });

                }

            };
            if (file) {
                reader.readAsDataURL(file);
            } else {
                preview.src = "";
            }


        }




//            var file = e.currentTarget.files[0];
//            var reader = new FileReader();
//
//            reader.onload = function (e) {
//                var dataURL = reader.result;
//                //alert("done here");
//                $('#lol').append(dataURL)
//                qrcode.decode(dataURL);
//            }
//
//            qrcode.callback = function (data) {
//                alert(data)
//            }
//
//            reader.readAsDataURL(file);


    });
    return  App.client.views.qrcode;
});