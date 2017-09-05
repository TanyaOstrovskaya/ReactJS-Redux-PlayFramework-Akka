export function sendPoint(x, y, r) {
    return dispatch => {

        console.log(x, y, r);

        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if ( xhr.readyState == 4 && xhr.status == 200 ) {
                console.log(xhr.responseText);
                dispatch (addPoint(x, y, xhr.responseText.localeCompare("1") === 0));
            }
        }
        xhr.open("GET", '/check?x=' + x + '&y=' + y +  '&r=' + r, true);
        xhr.send();
    }
}

export function addPoint (x, y, result) {
    return {
        type: 'ADD_POINT',
        x, y, result
    }
}

