/**
 * Created by dell on 2015/11/6.
 */

jQuery
    .extend({
        invenoRemoveUploadForm: function (id, newform, s) {
            if (!newform) return;
            var fileId = 'jUploadFile' + id;
            var oldElement = $('#' + fileId, newform);
            var newElement = $('#' + s.fileElementId);
            $(oldElement).attr('name', $(newElement).attr('name'));
            $(newElement).before(oldElement);
            $(newElement).remove();
            $(oldElement).attr('id', s.fileElementId);
            $(newform).remove();
        },
        invenoFormFileUpload: function (s) {
            // TODO introduce global settings, allowing the client to modify
            // them for all requests, not only timeout

            if (window.FormData == undefined) {
                jQuery.ajaxFileUpload(s);
                return;
            }

            var id = new Date().getTime();

            var newform = jQuery.createUploadForm(id, s.fileElementId,
                s.name);
            var formdata = new FormData(document
                .getElementById('jUploadForm' + id));
            var xhr = new XMLHttpRequest();

            // xhr.upload.addEventListener("progress", self.onProgress,
            // false);
            xhr.addEventListener("load", function (msg) {
                var responseText = msg.currentTarget.responseText;
                jQuery.invenoRemoveUploadForm(id, newform, s);
                s.success(responseText);
            }, false);
            xhr.addEventListener("error", function (msg) {
                jQuery.invenoRemoveUploadForm(id, newform, s);
                s.error();
            }, false);
            xhr.addEventListener("abort", function (msg) {
                jQuery.invenoRemoveUploadForm(id, newform, s);
            }, false);

            xhr.upload.onprogress = function (event) {
                var progressPercent = s.progressPercent || 100;

                if (event.lengthComputable) {

                    var percentComplete = event.loaded / event.total;
                    $("#" + s.progressBarId).css({
                        width: percentComplete * progressPercent + "%"
                    });

                }
            };

            /*xhr.onreadystatechange = function(){
             console.log(xhr);
             }*/
            xhr.open('post', s.url, true);

            xhr.send(formdata);
        },
        createUploadIframe: function (id, uri) {
            // create frame
            var frameId = 'jUploadFrame' + id;

            var io = document.createElement('iframe');
            io.id = frameId;
            io.name = frameId;
            io.style.position = 'absolute';
            io.style.top = '-1000px';
            io.style.left = '-1000px';

            document.body.appendChild(io);

            return io
        },
        createUploadForm: function (id, fileElementId, fieldName) {
            // create form
            var formId = 'jUploadForm' + id;
            var fileId = 'jUploadFile' + id;
            var form = $('<form  action="" method="POST" name="' + formId
            + '" id="' + formId
            + '" enctype="multipart/form-data"></form>');
            var oldElement = $('#' + fileElementId);
            var newElement = $(oldElement).clone();
            $(oldElement).attr('id', fileId);
            $(oldElement).attr('name', fieldName);
            $(oldElement).before(newElement);
            $(oldElement).appendTo(form);
            // set attributes
            $(form).css('position', 'absolute');
            $(form).css('top', '-1200px');
            $(form).css('left', '-1200px');
            $(form).appendTo('body');
            return form;
        },

        ajaxFileUpload: function (s) {
            // TODO introduce global settings, allowing the client to modify
            // them for all requests, not only timeout
            s = jQuery.extend({}, jQuery.ajaxSettings, s);
            var id = new Date().getTime()
            var form = jQuery.createUploadForm(id, s.fileElementId, s.name);
            var io = jQuery.createUploadIframe(id, s.secureuri);
            var frameId = 'jUploadFrame' + id;
            var formId = 'jUploadForm' + id;
            // Watch for a new set of requests
            if (s.global && !jQuery.active++) {
                jQuery.event.trigger("ajaxStart");
            }
            var requestDone = false;
            // Create the request object
            var xml = {}
            if (s.global)
                jQuery.event.trigger("ajaxSend", [xml, s]);
            // Wait for a response to come back
            var uploadCallback = function (isTimeout) {
                var io = document.getElementById(frameId);
                try {
                    if (io.contentWindow) {
                        xml.responseText = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML
                            : null;
                        xml.responseXML = io.contentWindow.document.XMLDocument ? io.contentWindow.document.XMLDocument
                            : io.contentWindow.document;

                    } else if (io.contentDocument) {
                        xml.responseText = io.contentDocument.document.body ? io.contentDocument.document.body.innerHTML
                            : null;
                        xml.responseXML = io.contentDocument.document.XMLDocument ? io.contentDocument.document.XMLDocument
                            : io.contentDocument.document;
                    }
                } catch (e) {
                    jQuery.handleError(s, xml, null, e);
                }
                if (xml || isTimeout == "timeout") {
                    requestDone = true;
                    var status;
                    try {
                        status = isTimeout != "timeout" ? "success"
                            : "error";
                        // Make sure that the request was successful or
                        // notmodified
                        if (status != "error") {
                            // process the data (runs the xml through
                            // httpData regardless of callback)
                            var data = jQuery.uploadHttpData(xml,
                                s.dataType);
                            // If a local callback was specified, fire it
                            // and pass it the data
                            if (s.success)
                                s.success(data, status);

                            // Fire the global callback
                            if (s.global)
                                jQuery.event.trigger("ajaxSuccess", [xml,
                                    s]);
                        } else
                            jQuery.handleError(s, xml, status);
                    } catch (e) {
                        status = "error";
                        jQuery.handleError(s, xml, status, e);
                    }

                    // The request was completed
                    if (s.global)
                        jQuery.event.trigger("ajaxComplete", [xml, s]);

                    // Handle the global AJAX counter
                    if (s.global && !--jQuery.active)
                        jQuery.event.trigger("ajaxStop");

                    // Process result
                    if (s.complete)
                        s.complete(xml, status);

                    jQuery(io).unbind()

                    setTimeout(function () {
                        try {
                            $(io).remove();
                            // $(form).remove();
                            jQuery.invenoRemoveUploadForm(id, form, s);
                            /* var fileId = 'jUploadFile' + id;
                             var oldElement = $('#' + fileId, form);
                             var newElement = $('#' + s.fileElementId);
                             $(oldElement).attr('name', $(newElement).attr('name'));
                             $(newElement).before(oldElement);
                             $(newElement).remove();
                             $(oldElement).attr('id', s.fileElementId);
                             $(form).remove();*/

                        } catch (e) {
                            jQuery.handleError(s, xml, null, e);
                        }

                    }, 100)

                    xml = null

                }
            }
            // Timeout checker
            if (s.timeout > 0) {
                setTimeout(function () {
                    // Check to see if the request is still happening
                    if (!requestDone)
                        uploadCallback("timeout");
                }, s.timeout);
            }
            try {
                // var io = $('#' + frameId);
                var form = $('#' + formId);
                $(form).attr('action', s.url);
                $(form).attr('method', 'POST');
                $(form).attr('target', frameId);
                if (form.encoding) {
                    form.encoding = 'multipart/form-data';
                } else {
                    form.enctype = 'multipart/form-data';
                }
                $(form).submit();

            } catch (e) {
                jQuery.handleError(s, xml, null, e);
            }
            if (window.attachEvent) {
                document.getElementById(frameId).attachEvent('onload',
                    uploadCallback);
            } else {
                document.getElementById(frameId).addEventListener('load',
                    uploadCallback, false);
            }
            return {
                abort: function () {
                }
            };

        },
        handleError: function (s, xhr, status, e) {
            // If a local callback was specified, fire it
            if (s.error) {
                s.error.call(s.context || s, xhr, status, e);
            }

            // Fire the global callback
            if (s.global) {
                (s.context ? jQuery(s.context) : jQuery.event).trigger(
                    "ajaxError", [xhr, s, e]);
            }
        },

        uploadHttpData: function (r, type) {
            var data = !type;
            data = type == "xml" || data ? r.responseXML : r.responseText;
            // If the type is "script", eval it in global context
            if (type == "script")
                jQuery.globalEval(data);
            // Get the JavaScript object, if JSON is used.
            if (type == "JSON") {
                eval("data = \" " + data + " \" ");
            }
            // evaluate scripts within html
            if (type == "html")
                jQuery("<div>").html(data).evalScripts();
            // alert($('param',
            // data).each(function(){alert($(this).attr('value'));}));
            return data;
        }
    })
