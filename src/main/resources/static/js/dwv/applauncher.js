///**
// * Application launcher.
// */
//
//// start app function
//
//
//// Image decoders (for web workers)
////dwv.image.decoderScripts = {
////    "jpeg2000": "/ext/pdfjs/decode-jpeg2000.js",
////    "jpeg-lossless": "/ext/rii-mango/decode-jpegloss.js",
////    "jpeg-baseline": "/ext/pdfjs/decode-jpegbaseline.js"
////};
//
//
//// status flags
//var domContentLoaded = false;
//var i18nLoaded = false;
//// launch when both DOM and i18n are ready
//function launchApp() {
//    if ( domContentLoaded && i18nLoaded ) {
//        startApp();
//    }
//}
//// DOM ready?
//document.addEventListener("DOMContentLoaded", function (/*event*/) {
//    domContentLoaded = true;
//    launchApp();
//});
//// i18n ready?
//dwv.i18nOnLoaded( function () {
//    i18nLoaded = true;
//    launchApp();
//});
