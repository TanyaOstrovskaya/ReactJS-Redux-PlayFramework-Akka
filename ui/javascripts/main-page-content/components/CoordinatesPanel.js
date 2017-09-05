import React from 'react';
import Button from 'react-toolbox/lib/button';
import Input from 'react-toolbox/lib/input';
import InteractiveArea from "./InteractiveArea"


export default class CoordinatesPanel extends React.Component {
    constructor(props) {
        super(props);
        this.x = 0;
        this.y = 0;
        this.r = 0;
    }
    componentDidMount() {
        document.getElementById("x_block").addEventListener("click", this.changeX.bind(this));
        document.getElementById("y").addEventListener("input", this.changeY.bind(this));
        document.getElementById("r_block").addEventListener("click", this.changeR.bind(this));
        document.getElementById("add-button").addEventListener("click", this.onAddNewPointBttnClick.bind(this));
    }
    changeX(e) {
        console.log( e.target.value);
        this.x = e.target.value;
    }
    changeY() {
        var y = document.getElementById('y').value;
        console.log(y);
        this.y = y;
    }
    changeR(e) {
        this.r = e.target.value;
        this.props.sendRadius (this.props.points, e.target.value);
    }
    onAddNewPointBttnClick()  {
        console.log (this.x, this.y, this.r);
        this.props.sendPoint(this.x, this.y, this.r, 1);
    }

    render () {
        return (
            <div>
                <div id="x_block">
                    {[-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2].map(function (number, i) {
                        return (
                            <label key={i}>
                                <Button name="x"  value={number}>{number}</Button>
                            </label>
                        )
                    }.bind(this))}
                </div>

                <div id="y_coord">
                    <Input type="text" label="Input Y" name='y' id="y"/>
                </div>

                <div id="r_block">
                    {[-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2].map(function (number, i) {
                        return (
                            <label key={i}>
                                <Button name="r" value={number}>{number}</Button>
                            </label>
                        )
                    }.bind(this))}
                </div>

                <div id="add-point-bttn">
                    <Button id="add-button">ADD POINT</Button>
                </div>
            </div>
        )
    }
}

CoordinatesPanel.propTypes = {
    points: React.PropTypes.array.isRequired,
    sendPoint: React.PropTypes.func.isRequired,
    sendRadius: React.PropTypes.func.isRequired
}
