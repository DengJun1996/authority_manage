let vheight = window.screen.height;
let vwidth = window.screen.width;
console.log('高度', vheight)
console.log('宽度', vwidth)

function detectZoom() {
	var ratio = 0,
		screen = window.screen,
		ua = navigator.userAgent.toLowerCase();

	if (window.devicePixelRatio !== undefined) {
		ratio = window.devicePixelRatio;
	} else if (~ua.indexOf('msie')) {
		if (screen.deviceXDPI && screen.logicalXDPI) {
			ratio = screen.deviceXDPI / screen.logicalXDPI;
		}
	} else if (window.outerWidth !== undefined && window.innerWidth !== undefined) {
		ratio = window.outerWidth / window.innerWidth;
	}

	if (ratio) {
		ratio = Math.round(ratio * 100);
	}
	return ratio;
}

let bili = detectZoom() / 1000;
console.log('屏幕缩放比', bili);


let screenWidth = window.screen.width;
if (screenWidth < 1920) {
	console.log('子界面宽度', true)
	document.getElementsByTagName('body')[0].style.zoom = 1 - bili;
}
