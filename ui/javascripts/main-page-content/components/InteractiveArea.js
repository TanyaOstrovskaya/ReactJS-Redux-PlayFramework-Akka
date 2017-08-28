import React from 'react';
import {isInArea} from "../isInArea"

export default class InteractiveArea extends React.Component {
    static get DEFAULT_RADIUS () {
        return 10;
    }
    constructor(props) {
        super(props);
        this.onCanvasClick = this.onCanvasClick.bind(this);
        this.getMouseClickCoordinates = this.getMouseClickCoordinates.bind(this);
    }
    componentDidMount() {
        document.getElementById("image").addEventListener("click", this.onCanvasClick.bind(this));
    }

    onCanvasClick (event) {
        const pos = this.getMouseClickCoordinates(event);
        const coord = this.modifyPositionToCoords(pos.x, pos.y, InteractiveArea.DEFAULT_RADIUS);
        const result = isInArea(coord.x, coord.y, InteractiveArea.DEFAULT_RADIUS);

        this.props.addPoint(coord.x, coord.y, result);
    }

    modifyPositionToCoords (x, y, r) {
        const canvas = document.getElementById("image");
        return {
            x: (x - canvas.offsetWidth/2) * 2 * r / canvas.offsetWidth,
            y: (-y + canvas.offsetHeight/2) * 2 * r / canvas.offsetHeight
        }
    }
    modifyCoordsToPosition (x, y, r) {
        const canvas = document.getElementById("image");
        return {
            x: x * canvas.offsetWidth / (2*r) + canvas.offsetWidth/2,
            y: -y * canvas.offsetHeight / (2*r) + canvas.offsetHeight/2
        }
    }

    getMouseClickCoordinates (event) {
        const canvas = document.getElementById("image");
        return {
            x: event.clientX - canvas.getBoundingClientRect().left,
            y: event.clientY - canvas.getBoundingClientRect().top
        }
    }

    render() {
        var pointDivs = [];
        if (document.getElementById("image")) {
            let parent = document.getElementById("image");
            var r = InteractiveArea.DEFAULT_RADIUS;
            pointDivs = this.props.points.map(function (point, i) {
                const offsetX = point.x / r * parent.clientWidth / 2 + parent.clientWidth / 2;
                const offsetY = -point.y / r * parent.clientHeight / 2 + parent.clientHeight / 2;
                let className = "point " + (point.result == true ? "in" : "out");
                return (
                    <div key={i} style={{top: offsetY + "px", left: offsetX + "px"}} className={className}/>
                )
            });
        } else {
            pointDivs = [];
        }

        return (
            <div id="img-container">
                {pointDivs}
                <img id="image" src="/assets/images/area_9.png"/>
            </div>
        );
    }
}

InteractiveArea.propTypes = {
    addPoint: React.PropTypes.func.isRequired,
    points: React.PropTypes.array.isRequired,
}