export function sendPoint(x, y, r, isnew) {
    return dispatch => {

        console.log(x, y, r);

        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if ( xhr.readyState == 4 && xhr.status == 200 ) {
                console.log(xhr.responseText);
                if (isnew > 0) {
                    dispatch (addPoint(x, y, xhr.responseText.localeCompare("1") === 0));
                } else  {
                    dispatch (updatePoint(x, y, xhr.responseText.localeCompare("1") === 0));
                }
            }
        }
        xhr.open("GET", '/check?x=' + x + '&y=' + y +  '&r=' + r + '&isnew=' + isnew, true);
        xhr.send();
    }
}

export function sendRadius (points, r) {
    return dispatch => {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if ( xhr.readyState == 4 && xhr.status == 200 ) {
                dispatch (changeRadius(r));

                points.forEach (function (point) {
                    dispatch(sendPoint (point.x, point.y, r, 0));
                })
            }
        }
        xhr.open("POST", '/change_radius?r=' + r, true);
        xhr.send();
    }
}

export function addPoint (x, y, result) {
    return {
        type: 'ADD_POINT',
        x, y, result
    }
}

export  function changeRadius (r) {
    return {
        type: 'CHANGE_R',
        r
    }
}

export function updatePoint(x, y, result) {
    return {
        type: 'UPDATE_POINT',
        x, y, result
    }
}