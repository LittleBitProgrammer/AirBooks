
function addStylesheet(path) {
    $('head').append('<link rel="stylesheet" type="text/css" href="' + path + '">');
}

function setup(fontSize, nightMode, goToEnd, startingPage) {
    var root = document.documentElement || document.getElementById("iframe-wrapper").contentWindow.document.documentElement;
    
    root.style.setProperty("--USER__pageMargins", "1.5rem");
    
    if (nightMode == true) {
        root.style.setProperty("--USER__backgroundColor", "rgb(19,19,19)");
        root.style.setProperty("--USER__textColor", "rgb(220,220,220)");
    } else {
        root.style.setProperty("--USER__backgroundColor", "rgb(251,251,251)");
        root.style.setProperty("--USER__textColor", "rgb(18,18,18)");
    }
    
    root.style.setProperty("--USER__fontSize", fontSize + "%");
    
    if (goToEnd == true) {
        window.scrollTo(document.body.scrollWidth, 0);
    }
    
    if (startingPage != null) {
        window.scrollTo(window.innerWidth * (startingPage - 1), 0);
    }
    
}

function setFontSize(size) {
    var root = document.documentElement || document.getElementById("iframe-wrapper").contentWindow.document.documentElement;
    root.style.setProperty("--USER__fontSize", size + "%");
}

function setNightMode(night) {
    var root = document.documentElement || document.getElementById("iframe-wrapper").contentWindow.document.documentElement;
    if (night == true) {
        root.style.setProperty("--USER__backgroundColor", "rgb(19,19,19)");
        root.style.setProperty("--USER__textColor", "rgb(220,220,220)");
    } else {
        root.style.setProperty("--USER__backgroundColor", "rgb(251,251,251)");
        root.style.setProperty("--USER__textColor", "rgb(18,18,18)");
    }
}
