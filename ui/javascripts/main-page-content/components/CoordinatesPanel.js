import React from 'react';
import Button from 'react-toolbox/lib/button';
import Input from 'react-toolbox/lib/input';
import {isInArea} from "../isInArea"
import InteractiveArea from "./InteractiveArea"


export default class CoordinatesPanel extends React.Component {
    constructor(props) {
        super(props);
        this.state = {x: "", y: "5", r: "" };
    }
    changeX(e) {
        this.setState({ x: e.target.value});
    }
    changeY() {
        var y = document.getElementsByName('y').value;
        this.setState({ y: y });
    }
    changeR(e) {
        this.setState({ r: e.target.value});
    }
    onAddNewPointBttnClick()  {
        console.log (this.state);
        const res = isInArea(this.state.x, this.state.y, InteractiveArea.DEFAULT_RADIUS);
        this.props.addPoint(this.state.x, this.state.y, res);
    }

    render () {
        return (
            <div>
                <div id="x_coord">
                    {[-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2].map(function (number, i) {
                        return (
                            <label key={i}>
                                <Button name="x" onClick={this.changeX.bind(this)} value={number}>{number}</Button>
                            </label>
                        )
                    }.bind(this))}
                </div>

                <div id="y_coord">
                    <Input type="text" label="Input Y" name='y' onChange={this.changeY.bind(this)}/>
                </div>

                <div id="r">
                    {[-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2].map(function (number, i) {
                        return (
                            <label key={i}>
                                <Button name="r" onClick={this.changeR.bind(this)} value={number}>{number}</Button>
                            </label>
                        )
                    }.bind(this))}
                </div>

                <div id="add-point-bttn">
                    <Button onClick={this.onAddNewPointBttnClick.bind(this)}>ADD POINT</Button>
                </div>
            </div>
        )
    }
}